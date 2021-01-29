package mc.Daoimpl;


import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceBuilder;
import mc.Component.KubernetesApiClient;
import mc.Dao.ServiceDao;
import mc.Entity.ServiceInfo;
import mc.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;
import java.util.HashMap;

import static io.kubernetes.client.extended.kubectl.Kubectl.delete;

@Repository
public class ServiceDaoImpl implements ServiceDao {
    @Autowired
    WebApplicationContext applicationContext;
    @Override
    public boolean createService(String serviceName, String port,Integer desiredReplicaNum,String image) {
        V1Service svc =
        new V1ServiceBuilder()
                .withApiVersion("v1")
                .withKind("Service")
                .withNewMetadata()
                .withName(serviceName)
                .withLabels(new HashMap<String,String>(){{put("app",serviceName);put("app.kubernetes.io/name","eztype");}})
                .endMetadata()
                .withNewSpec()
                .addNewPort()
                .withName("http")
                .withPort(Integer.parseInt(port))
                .endPort()
                .withSelector(new HashMap<String,String>(){{put("app",serviceName);}})
                .endSpec()
                .build();
        CoreV1Api api=applicationContext.getBean(KubernetesApiClient.class).getAPI();
        try {
            api.createNamespacedService("default", svc, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        ServiceRepository serviceRepository=applicationContext.getBean(ServiceRepository.class);
        ServiceInfo serviceInfo=new ServiceInfo();
        serviceInfo.setId(serviceName);
        serviceInfo.setImage(image);
        serviceInfo.setPort(port);
        serviceInfo.setTimestamp(Instant.now().toEpochMilli());
        serviceInfo.setDesiredReplicaNum(desiredReplicaNum);
        serviceRepository.save(serviceInfo);
        return true;
    }

    @Override
    public boolean deleteService(String serviceName) {
        try {
            delete(V1Service.class).namespace("default").name(serviceName).execute();
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        ServiceRepository serviceRepository=applicationContext.getBean(ServiceRepository.class);
        serviceRepository.deleteById(serviceName);
        return true;
    }
}
