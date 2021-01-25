package mc.Daoimpl;


import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceBuilder;
import mc.Component.KubernetesApiClient;
import mc.Dao.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import javax.jnlp.ServiceManager;
import java.util.HashMap;

@Repository
public class ServiceDaoImpl implements ServiceDao {
    @Autowired
    WebApplicationContext applicationContext;
    @Override
    public boolean createService(String serviceName, String port) {
        V1Service svc =
        new V1ServiceBuilder()
                .withApiVersion("v1")
                .withKind("Service")
                .withNewMetadata()
                .withName(serviceName)
                .withLabels(new HashMap<String,String>(){{put("app",serviceName);}})
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
        return true;
    }
}
