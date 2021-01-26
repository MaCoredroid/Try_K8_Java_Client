package mc.Task;

import io.kubernetes.client.Metrics;
import io.kubernetes.client.custom.NodeMetrics;
import io.kubernetes.client.custom.NodeMetricsList;
import io.kubernetes.client.custom.PodMetrics;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import mc.Component.KubernetesApiClient;
import mc.DTO.PodInfo;
import mc.Entity.NodeInfo;
import mc.Entity.ServiceInfo;
import mc.Repository.NodeRepository;
import mc.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;
@Component
public class CheckPodAndNodeUsage{
    @Autowired
    WebApplicationContext applicationContext;

    @Scheduled(cron ="3/1 * * * * *")
    public void run() throws ApiException {
        ApiClient client=applicationContext.getBean(KubernetesApiClient.class).getClient();
        ServiceRepository serviceRepository=applicationContext.getBean(ServiceRepository.class);
        NodeRepository nodeRepository=applicationContext.getBean(NodeRepository.class);

        Metrics metrics = new Metrics(client);
        NodeMetricsList list = metrics.getNodeMetrics();
        for (NodeMetrics item : list.getItems()) {
            if(nodeRepository.findById(Objects.requireNonNull(item.getMetadata().getName())).isPresent()) {
                NodeInfo nodeInfo = nodeRepository.findById(Objects.requireNonNull(item.getMetadata().getName())).get();
                nodeInfo.setNode_top_cpu_value(item.getUsage().get("cpu").getNumber().doubleValue());
                nodeInfo.setNode_top_cpu_percents(item.getUsage().get("cpu").getNumber().doubleValue() / nodeInfo.getNode_cpu_total());
                nodeRepository.save(nodeInfo);
            }
        }

        for (PodMetrics item : metrics.getPodMetrics("default").getItems()) {
            String podName=item.getMetadata().getName();
            String serviceName= Objects.requireNonNull(item.getMetadata().getName()).split("-", 2)[0];
            if(!serviceRepository.findById(serviceName).isPresent())
            {
                continue;
            }
            ServiceInfo serviceInfo=serviceRepository.findById(serviceName).orElseGet(ServiceInfo::new);
            if(serviceInfo.getPods().containsKey(podName))
            {

                if (item.getContainers() == null) {
                    continue;
                }
                PodInfo podInfo=serviceInfo.getPods().get(podName);
                podInfo.setCpu(item.getContainers().get(0).getUsage().get("cpu").getNumber().doubleValue());
                podInfo.setMemory(item.getContainers().get(0).getUsage().get("memory").getNumber().doubleValue());
            }
            serviceRepository.save(serviceInfo);

        }
    }
}
