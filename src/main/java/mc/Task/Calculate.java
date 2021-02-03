package mc.Task;

import mc.DTO.ExecutionDTO;
import mc.DTO.ExecutionDetailDTO;
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

import java.io.IOException;
import java.util.*;

@Component
public class Calculate {
    @Autowired
    WebApplicationContext applicationContext;

    @Scheduled(fixedDelay=3000)
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
            List<NodeInfo> nodeInfos=nodeRepository.findAll();
            List<String> IdList=new ArrayList<>();
            for(NodeInfo nodeInfo:nodeInfos)
            {
                IdList.add(nodeInfo.getId());
            }
            for (Map.Entry<String, PodInfo> entry : serviceInfo.getPods().entrySet()) {
                if(entry.getValue()==null)
                {
                    continue;
                }
                String nodeIP = entry.getValue().getNodeIP();
                if (nodeRepository.findByNodeIP(nodeIP).isPresent()) {
                    NodeInfo nodeInfo = nodeRepository.findByNodeIP(nodeIP).get();
                    WeightDTO weightDTO=new WeightDTO();
                    weightDTO.setNode(nodeInfo.getId());
                    IdList.remove(nodeInfo.getId());
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
                    if(weightDTO.getNowNodeLoad()>=1.0)
                    {
                        busyWeightDTOS.add(weightDTO);
                    }
                    else
                    {
                        idleWeightDTOS.add(weightDTO);
                    }
                }
            }
            if(count==0)
            {
                continue;
            }
            int rest=1000;
            int origin=1000/count;
            //Calculate available idle nodes
            List<String> fakeIdList=new ArrayList<>(IdList);
            for(String nodeId:fakeIdList)
            {
                NodeInfo nodeInfo = nodeRepository.findById(nodeId).get();
                if(nodeInfo.getNode_load_cpu_percents()>=1.0)
                {
                    IdList.remove(nodeId);
                }
            }
            IdList.remove("master1");
            IdList.remove("super");
            //decide to migrate
            int migrationNum=Math.min(busyWeightDTOS.size(),IdList.size());
            if(migrationNum!=0) {
                //Sort By weightDTO Now Node Load
                SortedMap<Double, WeightDTO> map = new TreeMap<>();
                for (WeightDTO weightDTO : busyWeightDTOS) {
                    map.put(weightDTO.getNowNodeLoad(), weightDTO);
                }
                System.out.println(migrationNum);
                System.out.println(map);
                for(Map.Entry<Double, WeightDTO> entry:map.entrySet())
                {
                    if(migrationNum==0)
                    {
                        break;
                    }
                    migrationNum--;
                    System.out.println(entry.getKey());
                }
            }
            //assign weight
            for(WeightDTO weightDTO:busyWeightDTOS)
            {
                int weight=0;
                if(weightDTO.getDeprecated())
                {
                    weightDTO.setWeight(weight);
                    executionDTO.getExecutionDetailDTOS().add(new ExecutionDetailDTO(weightDTO.getPodIP(),weightDTO.getWeight()));
                }
                else
                {
                    count--;
                    weight=(int)Math.round((origin/Math.sqrt(weightDTO.getNowNodeLoad()))*weightDTO.getPercents());
                    weightDTO.setWeight(weight);
                    executionDTO.getExecutionDetailDTOS().add(new ExecutionDetailDTO(weightDTO.getPodIP(),weightDTO.getWeight()));
                    rest-=weight;
                }
            }
            if(count!=0) {
                origin = rest / count;
                for (WeightDTO weightDTO : idleWeightDTOS) {
                    weightDTO.setWeight(origin);
                    executionDTO.getExecutionDetailDTOS().add(new ExecutionDetailDTO(weightDTO.getPodIP(), weightDTO.getWeight()));
                }
            }
            System.out.println(executionDTO);
            try {
                if(executionDTO.getExecutionDetailDTOS().size()!=0) {
                    execution.run(executionDTO);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
