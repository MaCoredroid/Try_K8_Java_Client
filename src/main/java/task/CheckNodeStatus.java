package task;

import io.kubernetes.client.openapi.apis.CoreV1Api;
import lombok.SneakyThrows;
import mc.DTO.NodeInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class CheckNodeStatus extends TimerTask {
    CoreV1Api api = new CoreV1Api();
    HashMap<String, NodeInfo> nodeMap=new HashMap<>();
    public CheckNodeStatus(CoreV1Api api, HashMap<String, NodeInfo> nodeMap)
    {
        this.api=api;
        this.nodeMap=nodeMap;
    }
    @SneakyThrows
    @Override
    public void run() {
        for(Map.Entry<String, NodeInfo> entry : nodeMap.entrySet()) {
            NodeInfo nodeInfo=entry.getValue();
            String nodeIP=entry.getKey();
            String command="curl http://"+nodeIP+":9100/metrics | grep 'node_memory_MemTotal_bytes\\|node_memory_MemAvailable_bytes\\|node_load1\\|mode=\"idle\"\\|node_time_seconds'";

            final Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
            new Thread(() -> {
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;

                try {
                    double time=0.0;
                    double count=0.0;
                    while ((line = input.readLine()) != null) {
                        if(!line.startsWith("#")) {

                            try {
                                if (line.startsWith("node_memory_MemTotal_bytes")) {
                                    double value = Double.parseDouble(line.replace("node_memory_MemTotal_bytes", "").trim());
                                    if(value!=0.0) {
                                        nodeInfo.setNode_memory_MemTotal_bytes(value);
                                    }
                                } else if (line.startsWith("node_memory_MemAvailable_bytes")) {
                                    double value = Double.parseDouble(line.replace("node_memory_MemAvailable_bytes", "").trim());
                                    if(value!=0.0) {
                                        nodeInfo.setNode_memory_MemAvailable_bytes(value);
                                        nodeInfo.setNode_memory_percents(1-value/nodeInfo.getNode_memory_MemTotal_bytes());
                                    }
                                } else if (line.startsWith("node_load1") && !line.startsWith("node_load15")) {
                                    double value = Double.parseDouble(line.replace("node_load1", "").trim());
                                    if(value!=0.0) {
                                        nodeInfo.setNode_load_cpu_1m(value);
                                        nodeInfo.setNode_load_cpu_percents(value/nodeInfo.getNode_cpu_total());
                                    }
                                } else if(line.startsWith("node_cpu_seconds_total")){
                                    count+=Double.parseDouble(line.trim().split(" ", 2)[1]);
                                } else if(line.startsWith("node_time_seconds")){
                                    time=Double.parseDouble(line.trim().split(" ", 2)[1]);
                                }
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }

                    }
                    double timeDiff=time-nodeInfo.getTotal_time();
                    double idleDiff=count-nodeInfo.getCpu_idle_time();
                    idleDiff=idleDiff/nodeInfo.getNode_cpu_total();
                    nodeInfo.setCpu_idle_percent(1-idleDiff/timeDiff);
                    System.out.println(nodeInfo.getNodeName());
                    System.out.println(count);
                    nodeInfo.setCpu_idle_time(count);
                    nodeInfo.setTotal_time(time);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            try {
                p.waitFor();
            } catch (Exception ignored) {

            }
            nodeMap.put(entry.getKey(),nodeInfo);
        }
//        System.out.println(nodeMap);
    }
}
