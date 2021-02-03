package mc.Task;

import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import mc.Component.KubernetesApiClient;
import mc.DTO.PodInfo;
import mc.Entity.ServiceInfo;
import mc.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public class CheckPodStatus{
    @Autowired
    WebApplicationContext applicationContext;

    @Scheduled(fixedDelay=500)
    public void run() {
        CoreV1Api api =applicationContext.getBean(KubernetesApiClient.class).getAPI();
        ServiceRepository serviceRepository=applicationContext.getBean(ServiceRepository.class);
        List<ServiceInfo> serviceInfos=serviceRepository.findAll();
        for(ServiceInfo serviceInfo:serviceInfos) {
            Set<String> podNameSet=new HashSet<>(serviceInfo.getPods().keySet());
            V1PodList list = null;
            try {
                list = api.listPodForAllNamespaces(null, null, null, "app="+serviceInfo.getId(), null, null, null, null, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (V1Pod item : Objects.requireNonNull(list).getItems()) {
                if(item.getMetadata()==null)
                {
                    continue;
                }
                if (Objects.equals(Objects.requireNonNull(item.getMetadata()).getNamespace(), "default")) {
                    if(Objects.requireNonNull(Objects.requireNonNull(item.getStatus()).getContainerStatuses()).size()==0)
                    {
                        continue;
                    }
                    if (Objects.requireNonNull(Objects.requireNonNull(item.getStatus()).getContainerStatuses()).get(0).getReady()) {
                        String podName = Objects.requireNonNull(item.getMetadata()).getName();
                        podNameSet.remove(podName);
                        if(!serviceInfo.getPods().containsKey(podName)) {
                            PodInfo podInfo = new PodInfo();
                            podInfo.setStartTimestamp(ZonedDateTime.now().toInstant().toEpochMilli());
                            podInfo.setPodName(podName);
                            podInfo.setNodeIP(Objects.requireNonNull(item.getStatus()).getHostIP());
                            podInfo.setPodIP(Objects.requireNonNull(item.getStatus()).getPodIP());
                            serviceInfo.getPods().put(podName, podInfo);
                        }
                    }
                }
            }
            for(String podName:podNameSet)
            {
                serviceInfo.getPods().remove(podName);
            }
            serviceInfo.setActualSize(list.getItems().size());
            serviceRepository.save(serviceInfo);
        }
    }
}
