package mc.Task;

import mc.DTO.ExecutionDTO;
import mc.DTO.ExecutionDetailDTO;
import mc.DTO.PodInfo;
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
import java.time.Instant;
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
            ExecutionDTO executionDTO=new ExecutionDTO();
            executionDTO.setServiceIP(serviceInfo.getClusterIP());
            if(Instant.now().toEpochMilli()-serviceInfo.getTimestamp()> 10000L *serviceInfo.getDesiredReplicaNum()) {
                System.out.println(serviceInfo.getId()+"     Reconciling!!!!");
                if (serviceInfo.getPods().size() != serviceInfo.getDesiredReplicaNum()) {
                    int diff = serviceInfo.getDesiredReplicaNum() - serviceInfo.getPods().size();
                    if (diff > 0) {
                        for (int i = 0; i < diff; i++) {
                            podDao.createPod(serviceInfo.getId(), serviceInfo.getImage(), serviceInfo.getPort());
                        }
                    } else {
                        diff *= -1;
                        for (Map.Entry<String, PodInfo> entry : serviceInfo.getPods().entrySet()) {
                            if (diff > 0) {
                                podDao.deletePod(entry.getValue().getPodName());
                                diff--;
                            } else {
                                break;
                            }
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    break;
                }
            }
            for (Map.Entry<String, PodInfo> entry : serviceInfo.getPods().entrySet()) {
                String nodeIP = entry.getValue().getNodeIP();
                if (nodeRepository.findByNodeIP(nodeIP).isPresent()) {
                    NodeInfo nodeInfo = nodeRepository.findByNodeIP(nodeIP).get();
                    executionDTO.getExecutionDetailDTOS().add(new ExecutionDetailDTO(entry.getValue().getPodIP(), 4));
                    System.out.println(serviceInfo.getId());
                    System.out.println("Node " + nodeInfo.getId());
                    System.out.println("Percents  " + entry.getValue().getCpu() / nodeInfo.getNode_top_cpu_value() + "  ");
                    System.out.println("Estimate  " + (nodeInfo.getNode_cpu_total() - nodeInfo.getNode_top_cpu_value()) / entry.getValue().getCpu() + "  ");
                    System.out.println("NowNode  " + nodeInfo.getNode_top_cpu_percents() + "  ");
                    System.out.println("NowNode  " + nodeInfo.getNode_load_cpu_percents() + "  ");
                    System.out.println("NowNode  " + nodeInfo.getCpu_idle_percent() + "\n");

                }
            }
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
