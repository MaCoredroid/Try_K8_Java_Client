package task;

import io.kubernetes.client.custom.NodeMetrics;
import io.kubernetes.client.custom.PodMetrics;
import io.kubernetes.client.custom.PodMetricsList;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1Pod;
import lombok.SneakyThrows;
import mc.DTO.NodeInfo;
import mc.DTO.PodInfo;
import mc.DTO.ServiceInfo;
import org.apache.commons.lang3.tuple.Pair;
import org.jose4j.json.internal.json_simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.TimerTask;

import static io.kubernetes.client.extended.kubectl.Kubectl.top;

public class CheckPodAndNodeUsage extends TimerTask {
    private final ApiClient client;
    HashMap<String, NodeInfo> nodeMap=new HashMap<>();
    HashMap<String, ServiceInfo> serviceNameMap = new HashMap<>();
    public CheckPodAndNodeUsage(ApiClient client,HashMap<String, NodeInfo> nodeMap,HashMap<String, ServiceInfo> serviceNameMap)
    {
        this.client=client;
        this.nodeMap=nodeMap;
        this.serviceNameMap=serviceNameMap;
    }
    @SneakyThrows
    @Override
    public void run() {
        List<Pair<V1Node, NodeMetrics>> nodesMetrics = top(V1Node.class, NodeMetrics.class).apiClient(client).execute();
        for(Pair<V1Node, NodeMetrics> nodeMetricsPair:nodesMetrics) {
            String nodeIP= Objects.requireNonNull(Objects.requireNonNull(nodeMetricsPair.getLeft().getStatus()).getAddresses()).get(0).getAddress();
            NodeInfo nodeInfo=nodeMap.getOrDefault(nodeIP,new NodeInfo());
            nodeInfo.setNode_top_cpu_value(nodeMetricsPair.getRight().getUsage().get("cpu").getNumber().doubleValue());
            nodeInfo.setNode_top_cpu_percents(nodeMetricsPair.getRight().getUsage().get("cpu").getNumber().doubleValue()/nodeInfo.getNode_cpu_total());
            nodeMap.put(nodeIP,nodeInfo);
        }
        List<Pair<V1Pod, PodMetrics>> podsMetrics = top(V1Pod.class, PodMetrics.class).apiClient(client).namespace("default").execute();
        for(Pair<V1Pod, PodMetrics> podMetricsPair:podsMetrics) {
            String podName=podMetricsPair.getRight().getMetadata().getName();
            String serviceName= Objects.requireNonNull(podName).split("-", 2)[0];
            if(serviceNameMap.getOrDefault(serviceName, new ServiceInfo()).getPods().containsKey(podName))
            {
                PodInfo podInfo=serviceNameMap.getOrDefault(serviceName, new ServiceInfo()).getPods().get(podName);
                podInfo.setCpu(podMetricsPair.getRight().getContainers().get(0).getUsage().get("cpu").getNumber().doubleValue());
                podInfo.setMemory(podMetricsPair.getRight().getContainers().get(0).getUsage().get("memory").getNumber().doubleValue());
            }

        }
    }
}
