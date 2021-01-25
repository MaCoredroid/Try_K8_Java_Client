package mc.Task;

import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeList;
import mc.DTO.NodeInfo;

import java.util.HashMap;
import java.util.Objects;
import java.util.TimerTask;

public class CheckNodeList extends TimerTask {
    CoreV1Api api = new CoreV1Api();
    HashMap<String, NodeInfo> nodeMap=new HashMap<>();
    public CheckNodeList(CoreV1Api api, HashMap<String, NodeInfo> nodeMap){
        this.api=api;
        this.nodeMap=nodeMap;
    }
    @Override
    public void run() {
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
            NodeInfo nodeInfo=nodeMap.getOrDefault(nodeIP, new NodeInfo());
            nodeInfo.setNodeName(nodeName);
            nodeInfo.setNode_cpu_total(Objects.requireNonNull(Objects.requireNonNull(node.getStatus()).getCapacity()).get("cpu").getNumber().doubleValue());
            nodeMap.put(nodeIP,nodeInfo);
        }
    }
}
