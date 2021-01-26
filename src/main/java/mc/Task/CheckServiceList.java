package mc.Task;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceList;
import mc.Component.KubernetesApiClient;
import mc.Entity.ServiceInfo;
import mc.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

@Component
public class CheckServiceList{
    @Autowired
    WebApplicationContext applicationContext;

    @Scheduled(cron ="*/0.5 * * * * *")
    public void run() {
        CoreV1Api api =applicationContext.getBean(KubernetesApiClient.class).getAPI();
        ServiceRepository serviceRepository=applicationContext.getBean(ServiceRepository.class);
        V1ServiceList serviceList = null;
        try {
            serviceList = api.listServiceForAllNamespaces(null, null, null, null, null, null, null, null, null,null);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        for (V1Service item : Objects.requireNonNull(serviceList).getItems()) {
            if (Objects.equals(Objects.requireNonNull(item.getMetadata()).getNamespace(), "default")) {
                ServiceInfo serviceInfo=serviceRepository.findById(Objects.requireNonNull(item.getMetadata().getName())).orElseGet(ServiceInfo::new);
                serviceInfo.setServiceName(item.getMetadata().getName());
                serviceInfo.setClusterIP(Objects.requireNonNull(item.getSpec()).getClusterIP());
                serviceRepository.save(serviceInfo);
            }
        }
        System.out.println(serviceRepository.findAll());
    }
}
