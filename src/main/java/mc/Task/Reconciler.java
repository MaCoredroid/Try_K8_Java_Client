package mc.Task;

import mc.DTO.PodInfo;
import mc.Dao.PodDao;
import mc.Entity.ServiceInfo;
import mc.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Component
public class Reconciler {
    @Autowired
    WebApplicationContext applicationContext;

    @Scheduled(fixedDelay=1000)
    public void run() {
        ServiceRepository serviceRepository=applicationContext.getBean(ServiceRepository.class);
        PodDao podDao=applicationContext.getBean(PodDao.class);
        List<ServiceInfo> serviceInfos=serviceRepository.findAll();
        for(ServiceInfo serviceInfo:serviceInfos) {
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
                    serviceInfo.setTimestamp(Instant.now().toEpochMilli());
                    serviceRepository.save(serviceInfo);
                }
            }
        }

    }
}
