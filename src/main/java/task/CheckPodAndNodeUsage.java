package task;

import io.kubernetes.client.custom.NodeMetrics;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.V1Node;
import lombok.SneakyThrows;
import mc.DTO.NodeInfo;
import org.apache.commons.lang3.tuple.Pair;
import org.jose4j.json.internal.json_simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

import static io.kubernetes.client.extended.kubectl.Kubectl.top;
;
public class CheckPodAndNodeUsage extends TimerTask {
    private final ApiClient client;
    HashMap<String, NodeInfo> nodeMap=new HashMap<>();
    public CheckPodAndNodeUsage(ApiClient client,HashMap<String, NodeInfo> nodeMap)
    {
        this.client=client;
        this.nodeMap=nodeMap;
    }
    @SneakyThrows
    @Override
    public void run() {
        List<Pair<V1Node, NodeMetrics>> nodesMetrics = top(V1Node.class, NodeMetrics.class).apiClient(client).metric("cpu").execute();
        for(Pair<V1Node, NodeMetrics> nodeMetricsPair:nodesMetrics) {
            String nodeName=nodeMetricsPair.getRight().getMetadata().getName();
            NodeInfo nodeInfo=nodeMap.getOrDefault(nodeName,new NodeInfo());
            nodeInfo.setNode_top_cpu_value(nodeMetricsPair.getRight().getUsage().get("cpu").getNumber().doubleValue());
            nodeInfo.setNode_top_cpu_percents(nodeMetricsPair.getRight().getUsage().get("cpu").getNumber().doubleValue()/nodeInfo.getNode_cpu_total());
            nodeMap.put(nodeName,nodeInfo);
        }
        System.out.println(new JSONObject(nodeMap).toJSONString());
    }
}
