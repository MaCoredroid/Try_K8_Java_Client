package task;

import mc.DTO.NodeInfo;
import mc.DTO.ServiceInfo;

import java.util.HashMap;
import java.util.TimerTask;

public class Calculate extends TimerTask {
    HashMap<String, ServiceInfo> serviceNameMap = new HashMap<>();
    HashMap<String, NodeInfo> nodeMap=new HashMap<>();
    public Calculate(HashMap<String, ServiceInfo> serviceNameMap,HashMap<String, NodeInfo> nodeMap)
    {
        this.serviceNameMap=serviceNameMap;
        this.nodeMap=nodeMap;
    }
    @Override
    public void run() {
        System.out.println(serviceNameMap);
        System.out.println("%n");
        System.out.println(nodeMap);
        System.out.println("%n");
    }
}
