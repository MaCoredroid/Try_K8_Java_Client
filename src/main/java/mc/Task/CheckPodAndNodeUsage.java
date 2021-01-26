//package mc.Task;
//
//import io.kubernetes.client.Metrics;
//import io.kubernetes.client.custom.NodeMetrics;
//import io.kubernetes.client.custom.NodeMetricsList;
//import io.kubernetes.client.custom.PodMetrics;
//import io.kubernetes.client.openapi.ApiClient;
//import lombok.SneakyThrows;
//import mc.DTO.NodeInfo;
//import mc.DTO.PodInfo;
//import mc.DTO.ServiceInfo;
//
//import java.util.HashMap;
//import java.util.Objects;
//import java.util.TimerTask;
//
//public class CheckPodAndNodeUsage extends TimerTask {
//    private final ApiClient client;
//    HashMap<String, NodeInfo> nodeMap=new HashMap<>();
//    HashMap<String, ServiceInfo> serviceNameMap = new HashMap<>();
//    HashMap<String, String> nodeNameToIP=new HashMap<>();
//    public CheckPodAndNodeUsage(ApiClient client,HashMap<String, NodeInfo> nodeMap,HashMap<String, ServiceInfo> serviceNameMap,HashMap<String, String> nodeNameToIP)
//    {
//        this.client=client;
//        this.nodeMap=nodeMap;
//        this.serviceNameMap=serviceNameMap;
//        this.nodeNameToIP=nodeNameToIP;
//    }
//    @SneakyThrows
//    @Override
//    public void run() {
//        Metrics metrics = new Metrics(client);
//        NodeMetricsList list = metrics.getNodeMetrics();
//        for (NodeMetrics item : list.getItems()) {
//            String nodeIP=nodeNameToIP.get(item.getMetadata().getName());
//            NodeInfo nodeInfo=nodeMap.getOrDefault(nodeIP,new NodeInfo());
//            nodeInfo.setNode_top_cpu_value(item.getUsage().get("cpu").getNumber().doubleValue());
//            nodeInfo.setNode_top_cpu_percents(item.getUsage().get("cpu").getNumber().doubleValue()/nodeInfo.getNode_cpu_total());
//            nodeMap.put(nodeIP,nodeInfo);
//        }
//
//        for (PodMetrics item : metrics.getPodMetrics("default").getItems()) {
//            String podName=item.getMetadata().getName();
//            String serviceName= Objects.requireNonNull(item.getMetadata().getName()).split("-", 2)[0];
//            if(serviceNameMap.getOrDefault(serviceName, new ServiceInfo()).getPods().containsKey(podName))
//            {
//
//                if (item.getContainers() == null) {
//                    continue;
//                }
//                PodInfo podInfo=serviceNameMap.getOrDefault(serviceName, new ServiceInfo()).getPods().get(podName);
//                podInfo.setCpu(item.getContainers().get(0).getUsage().get("cpu").getNumber().doubleValue());
//                podInfo.setMemory(item.getContainers().get(0).getUsage().get("memory").getNumber().doubleValue());
//            }
//
//        }
////        System.out.println(serviceNameMap);
//    }
//}
