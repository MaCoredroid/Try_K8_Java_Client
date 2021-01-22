package task;

import mc.DTO.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        ExecutionDTO executionDTO=new ExecutionDTO();
        executionDTO.setServiceIP(serviceNameMap.getOrDefault("application", new ServiceInfo()).getClusterIP());

        for(Map.Entry<String, PodInfo> entry:serviceNameMap.getOrDefault("application", new ServiceInfo()).getPods().entrySet())
        {
            String nodeIP=entry.getValue().getNodeIP();
            System.out.println(entry.getValue().getPodName()+" ");
            if(nodeMap.containsKey(nodeIP))
            {
                NodeInfo nodeInfo=nodeMap.get(nodeIP);
                executionDTO.getExecutionDetailDTOS().add(new ExecutionDetailDTO(nodeIP,0));
//                System.out.println("Node "+nodeInfo.getNodeName());
//                System.out.println("Percents  "+entry.getValue().getCpu()/nodeInfo.getNode_top_cpu_value()+"  ");
//                System.out.println("Estimate  "+(nodeInfo.getNode_cpu_total()-nodeInfo.getNode_top_cpu_value())/entry.getValue().getCpu()+"  ");
//                System.out.println("NowNode  "+nodeInfo.getNode_top_cpu_percents()+"  ");
//                System.out.println("NowNode  "+nodeInfo.getNode_load_cpu_percents()+"  ");
//                System.out.println("NowNode  "+nodeInfo.getCpu_idle_percent()+"\n");
            }

        }


        Execution execution=new Execution(nodeMap,executionDTO);
        execution.run();
    }
}
