package mc.Task;

import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeList;
import mc.Component.KubernetesApiClient;
import mc.Entity.NodeInfo;
import mc.Repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import java.util.Objects;

@Component
public class CheckNodeList  {
    @Autowired
    WebApplicationContext applicationContext;

    @Scheduled(fixedDelay=1000)
    public void run() {
        CoreV1Api api =applicationContext.getBean(KubernetesApiClient.class).getAPI();
        NodeRepository nodeRepository=applicationContext.getBean(NodeRepository.class);
        nodeRepository.deleteAll();
        V1NodeList nodeList=null;
        try {
            nodeList = api.listNode(null, null, null, null, null, null, null, null, null,null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        for(V1Node node: Objects.requireNonNull(nodeList).getItems())
        {
            String nodeIP= Objects.requireNonNull(Objects.requireNonNull(node.getStatus()).getAddresses()).get(0).getAddress();
            String nodeName= Objects.requireNonNull(node.getMetadata()).getName();
            assert nodeName != null;
            NodeInfo nodeInfo=nodeRepository.findById(nodeName).orElseGet(NodeInfo::new);
            nodeInfo.setId(nodeName);
            nodeInfo.setNodeIP(nodeIP);
            nodeInfo.setNode_cpu_total(Objects.requireNonNull(Objects.requireNonNull(node.getStatus()).getCapacity()).get("cpu").getNumber().doubleValue());
            nodeInfo.setApplicable(!nodeName.equals("master1") && !nodeName.equals("super"));
            nodeRepository.save(nodeInfo);
        }
    }
}
