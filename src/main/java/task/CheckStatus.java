package task;

import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import lombok.SneakyThrows;
import mc.DTO.NodeInfo;
import mc.DTO.PodInfo;
import mc.DTO.ServiceInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class CheckStatus extends TimerTask {
    CoreV1Api api = new CoreV1Api();

    HashMap<String, ServiceInfo> serviceNameMap = new HashMap<>();
    HashMap<String, NodeInfo> nodeMap=new HashMap<>();
    public CheckStatus(CoreV1Api api,HashMap<String, ServiceInfo> serviceNameMap,HashMap<String, NodeInfo> nodeMap)
    {
        this.api=api;
        this.serviceNameMap=serviceNameMap;
        this.nodeMap=nodeMap;
    }
    @SneakyThrows
    @Override
    public void run() {
        V1PodList list = null;
        try {
             list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        for (V1Pod item : list.getItems()) {
            if (Objects.equals(Objects.requireNonNull(item.getMetadata()).getNamespace(), "default")) {
                String[] arrOfStr = Objects.requireNonNull(Objects.requireNonNull(item.getMetadata()).getName()).split("-", 2);
                String serviceName=arrOfStr[0];
                if(!serviceNameMap.containsKey(serviceName))
                {
                    continue;
                }
                serviceNameMap.get(serviceName).getPodInfos().add(new PodInfo(Objects.requireNonNull(item.getMetadata()).getName(),Objects.requireNonNull(item.getStatus()).getPodIP(),Objects.requireNonNull(item.getStatus()).getHostIP()));
            }

        }
        for(Map.Entry<String, NodeInfo> entry : nodeMap.entrySet()) {
            NodeInfo nodeInfo=entry.getValue();
            String nodeIP=entry.getKey();
            String command="curl http://"+nodeIP+":9100/metrics | grep 'node_memory_MemTotal_bytes\\|node_memory_MemAvailable_bytes\\|node_load1'";

            final Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});

            AtomicBoolean flag= new AtomicBoolean(false);
            new Thread(() -> {
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;

                try {
                    while ((line = input.readLine()) != null) {
                        if(!line.startsWith("#")) {
                            try {
                                if (line.startsWith("node_memory_MemTotal_bytes")) {
                                    double value = Double.parseDouble(line.replace("node_memory_MemTotal_bytes", "").trim());
                                    if(value!=0.0) {
                                        flag.set(true);
                                        nodeInfo.setNode_memory_MemTotal_bytes(value);
                                    }
                                } else if (line.startsWith("node_memory_MemAvailable_bytes")) {
                                    double value = Double.parseDouble(line.replace("node_memory_MemAvailable_bytes", "").trim());
                                    if(value!=0.0) {
                                        flag.set(true);
                                        nodeInfo.setNode_memory_MemAvailable_bytes(value);
                                    }
                                } else if (line.startsWith("node_load1") && !line.startsWith("node_load15")) {
                                    double value = Double.parseDouble(line.replace("node_load1", "").trim());
                                    if(value!=0.0) {
                                        flag.set(true);
                                        nodeInfo.setNode_load_1m(value);
                                    }
                                }
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            try {
                p.waitFor();
            } catch (Exception ignored) {

            }
            if(flag.get())
            {
                System.out.println(nodeInfo);
            }
            nodeMap.put(nodeIP,nodeInfo);
        }
    }
}
