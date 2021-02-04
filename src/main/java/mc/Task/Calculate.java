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
import java.time.ZonedDateTime;
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
                if(ZonedDateTime.now().toInstant().toEpochMilli()-entry.getValue().getStartTimestamp()<=12000)
                {
                    continue;
                }
                String nodeIP = entry.getValue().getNodeIP();
                if (nodeRepository.findByNodeIP(nodeIP).isPresent()) {
                    NodeInfo nodeInfo = nodeRepository.findByNodeIP(nodeIP).get();
                    WeightDTO weightDTO=new WeightDTO();
                    weightDTO.setNode(nodeInfo.getId());
                    weightDTO.setPodIP(entry.getValue().getPodIP());
                    weightDTO.setPodName(entry.getValue().getPodName());
                    weightDTO.setPercents(entry.getValue().getCpu() / nodeInfo.getNode_top_cpu_value());
                    weightDTO.setEstimate(nodeInfo.getNode_cpu_total() - nodeInfo.getNode_top_cpu_value()/ entry.getValue().getCpu());
                    weightDTO.setNowNodeTop(nodeInfo.getNode_top_cpu_percents());
                    weightDTO.setNowNodeLoad(nodeInfo.getNode_load_cpu_percents());
                    weightDTO.setNowNodePercents(nodeInfo.getCpu_idle_percent());
                    if(entry.getValue().getDeprecatedFlag()&&ZonedDateTime.now().toInstant().toEpochMilli()-entry.getValue().getDeprecatedTimestamp()>=12000) {
                        weightDTO.setDeprecated(true);
                    }
                    if(!weightDTO.getDeprecated())
                    {
                        count++;
                    }
                    IdList.remove(nodeInfo.getId());
                    if(weightDTO.getNowNodeLoad()>2.0&&!weightDTO.getDeprecated())
                    {
                        busyWeightDTOS.add(weightDTO);
                    }
                    else if(!weightDTO.getDeprecated())
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
                if(nodeInfo.getNode_load_cpu_percents()>=2.0)
                {
                    IdList.remove(nodeId);
                }
                if(!nodeInfo.getApplicable())
                {
                    IdList.remove(nodeId);
                }
            }
            IdList.remove("master1");
            IdList.remove("super");
            System.out.println(IdList);
            //decide to migrate
            int migrationNum=Math.min(busyWeightDTOS.size(),IdList.size());
            if(migrationNum!=0) {
                //Sort By weightDTO Now Node Load
                NavigableMap<Double, WeightDTO> map = new TreeMap<>();
                for (WeightDTO weightDTO : busyWeightDTOS) {
                    map.put(weightDTO.getNowNodeLoad(), weightDTO);
                }
                NavigableMap<Double, WeightDTO> reversedTreeMap = map.descendingMap();
                int numberOfMigration=0;
                for(Map.Entry<Double, WeightDTO> entry:reversedTreeMap.entrySet())
                {
                    if(numberOfMigration==migrationNum)
                    {
                        break;
                    }
                    System.out.println(entry.getValue());
                    podDao.createPodWithSelectedNode(serviceInfo.getId(), serviceInfo.getImage(), serviceInfo.getPort(),IdList.get(numberOfMigration));
                    NodeInfo nodeInfo = nodeRepository.findById(IdList.get(numberOfMigration)).get();
                    nodeInfo.setApplicable(false);
                    nodeRepository.save(nodeInfo);
                    numberOfMigration++;
                    serviceInfo.getPods().get(entry.getValue().getPodName()).setDeprecatedFlag(true);
                    serviceInfo.getPods().get(entry.getValue().getPodName()).setDeprecatedTimestamp(ZonedDateTime.now().toInstant().toEpochMilli());
                    serviceRepository.save(serviceInfo);
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
                    if(weightDTO.getDeprecated())
                    {
                        weightDTO.setWeight(0);
                    }
                    else
                    {
                        weightDTO.setWeight(origin);
                    }
                    executionDTO.getExecutionDetailDTOS().add(new ExecutionDetailDTO(weightDTO.getPodIP(),weightDTO.getWeight()));
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
