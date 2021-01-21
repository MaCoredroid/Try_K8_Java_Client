package task;

import io.kubernetes.client.custom.NodeMetrics;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.V1Node;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.TimerTask;

import static io.kubernetes.client.extended.kubectl.Kubectl.top;
;
public class CheckPodUsage extends TimerTask {
    private final ApiClient client;
    public CheckPodUsage(ApiClient client)
    {
        this.client=client;
    }
    @SneakyThrows
    @Override
    public void run() {
        List<Pair<V1Node, NodeMetrics>> nodesMetrics = top(V1Node.class, NodeMetrics.class).apiClient(client).metric("cpu").execute();
        for(Pair<V1Node, NodeMetrics> nodeMetricsPair:nodesMetrics) {
            System.out.println(nodeMetricsPair.getRight().getMetadata().getName());
            System.out.println(nodeMetricsPair.getRight().getUsage());
        }
    }
}
