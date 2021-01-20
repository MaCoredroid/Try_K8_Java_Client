package task;

import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import mc.DTO.PodInfo;
import mc.DTO.ServiceInfo;

import java.util.HashMap;
import java.util.Objects;
import java.util.TimerTask;

public class CheckPodStatus extends TimerTask {
    CoreV1Api api = new CoreV1Api();
    HashMap<String, ServiceInfo> serviceNameMap = new HashMap<>()
    public CheckPodStatus(CoreV1Api api, HashMap<String, ServiceInfo> serviceNameMap){
        this.api=api;
        this.serviceNameMap=serviceNameMap;
    }
    @Override
    public void run() {
        V1PodList list = null;
        try {
            list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        for (V1Pod item : Objects.requireNonNull(list).getItems()) {
            if (Objects.equals(Objects.requireNonNull(item.getMetadata()).getNamespace(), "default")) {
                String[] arrOfStr = Objects.requireNonNull(Objects.requireNonNull(item.getMetadata()).getName()).split("-", 2);
                String serviceName=arrOfStr[0];
                if(!serviceNameMap.containsKey(serviceName))
                {
                    continue;
                }
                serviceNameMap.get(serviceName).getPods().put(Objects.requireNonNull(item.getMetadata()).getName(),new PodInfo(Objects.requireNonNull(item.getMetadata()).getName(),Objects.requireNonNull(item.getStatus()).getPodIP(),Objects.requireNonNull(item.getStatus()).getHostIP()));
            }

        }
        System.out.println(serviceNameMap);
    }
}
