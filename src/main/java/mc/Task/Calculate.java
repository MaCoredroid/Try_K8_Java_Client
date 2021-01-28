package mc.Task;

import mc.DTO.ExecutionDTO;
import mc.DTO.PodInfo;
import mc.DTO.WeightDTO;
import mc.Dao.PodDao;
import mc.Entity.NodeInfo;
import mc.Entity.ServiceInfo;
import mc.Repository.NodeRepository;
import mc.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Calculate {
    @Autowired
    WebApplicationContext applicationContext;

    @Scheduled(fixedDelay=1000)
    public void run() {
        ServiceRepository serviceRepository=applicationContext.getBean(ServiceRepository.class);
        NodeRepository nodeRepository=applicationContext.getBean(NodeRepository.class);
        PodDao podDao=applicationContext.getBean(PodDao.class);
        Execution execution=applicationContext.getBean(Execution.class);
        List<ServiceInfo> serviceInfos=serviceRepository.findAll();
        for(ServiceInfo serviceInfo:serviceInfos) {
            List<WeightDTO> idleWeightDTOS=new ArrayList<>();
            List<WeightDTO> busyWeightDTOS=new ArrayList<>();
            int count=0;
            ExecutionDTO executionDTO=new ExecutionDTO();
            executionDTO.setServiceIP(serviceInfo.getClusterIP());
            for (Map.Entry<String, PodInfo> entry : serviceInfo.getPods().entrySet()) {
                String nodeIP = entry.getValue().getNodeIP();
                if (nodeRepository.findByNodeIP(nodeIP).isPresent()) {
                    NodeInfo nodeInfo = nodeRepository.findByNodeIP(nodeIP).get();
                    System.out.println(serviceInfo.getId());
                    WeightDTO weightDTO=new WeightDTO();
                    weightDTO.setNode(nodeInfo.getId());
                    weightDTO.setPodIP(entry.getValue().getPodIP());
                    weightDTO.setPercents(entry.getValue().getCpu() / nodeInfo.getNode_top_cpu_value());
                    weightDTO.setEstimate(nodeInfo.getNode_cpu_total() - nodeInfo.getNode_top_cpu_value()/ entry.getValue().getCpu());
                    weightDTO.setNowNodeTop(nodeInfo.getNode_top_cpu_percents());
                    weightDTO.setNowNodeLoad(nodeInfo.getNode_load_cpu_percents());
                    weightDTO.setNowNodePercents(nodeInfo.getCpu_idle_percent());
                    weightDTO.setDeprecated(entry.getValue().getDeprecatedFlag());
                    if(!entry.getValue().getDeprecatedFlag())
                    {
                        count++;
                    }
                    if(nodeInfo.getCpu_idle_percent()>0.95)
                    {
                        busyWeightDTOS.add(weightDTO);
                    }
                    else
                    {
                        idleWeightDTOS.add(weightDTO);
                    }
                }
            }
            int rest=100;
            int origin=100/count;
            for(WeightDTO weightDTO:busyWeightDTOS)
            {
                int weight=0;
                count--;
                if(weightDTO.getDeprecated())
                {
                    weightDTO.setWeight(weight);

                }
                else
                {
                    weight=(int)Math.round(origin/Math.sqrt(weightDTO.getNowNodeLoad()));
                    weightDTO.setWeight(weight);
                    rest-=weight;
                }
            }
            origin=rest/count;
            for(WeightDTO weightDTO:idleWeightDTOS)
            {
                weightDTO.setWeight(origin);
            }

//            try {
//                if(executionDTO.getExecutionDetailDTOS().size()!=0) {
//                    execution.run(executionDTO);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

    }
}
