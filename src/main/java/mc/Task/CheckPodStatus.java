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

import java.util.Objects;

@Component
public class CheckPodStatus{
    @Autowired
    WebApplicationContext applicationContext;

    @Scheduled(cron ="3/1 * * * * *")
    public void run() {
        CoreV1Api api =applicationContext.getBean(KubernetesApiClient.class).getAPI();
        ServiceRepository serviceRepository=applicationContext.getBean(ServiceRepository.class);
        V1PodList list = null;
        try {
            list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null,null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        for (V1Pod item : Objects.requireNonNull(list).getItems()) {
            if (Objects.equals(Objects.requireNonNull(item.getMetadata()).getNamespace(), "default")) {
                String[] arrOfStr = Objects.requireNonNull(Objects.requireNonNull(item.getMetadata()).getName()).split("-", 2);
                String serviceName=arrOfStr[0];
                if(!serviceRepository.findById(serviceName).isPresent())
                {
                    continue;
                }
                String podName=Objects.requireNonNull(item.getMetadata()).getName();
                ServiceInfo serviceInfo=serviceRepository.findById(serviceName).get();
                PodInfo podInfo=serviceInfo.getPods().getOrDefault(podName, new PodInfo());
                podInfo.setPodName(podName);
                podInfo.setNodeIP(Objects.requireNonNull(item.getStatus()).getHostIP());
                podInfo.setPodIP(Objects.requireNonNull(item.getStatus()).getPodIP());
                serviceInfo.getPods().put(podName,podInfo);
                serviceRepository.save(serviceInfo);
            }

        }
//        System.out.println(serviceNameMap);
    }
}
