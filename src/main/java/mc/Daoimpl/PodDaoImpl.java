package mc.Daoimpl;

import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodBuilder;
import mc.Component.KubernetesApiClient;
import mc.Dao.PodDao;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static io.kubernetes.client.extended.kubectl.Kubectl.delete;

@Repository
public class PodDaoImpl implements PodDao {
    @Autowired
    WebApplicationContext applicationContext;
    @Override
    public boolean createPod(String serviceName, String image, String port) {
        V1Pod pod =
        new V1PodBuilder()
                        .withApiVersion("v1")
                        .withKind("Pod")
                        .withNewMetadata()
                        .withName(serviceName+"-"+ RandomStringUtils.random(10, true, true).toLowerCase()+"-"+RandomStringUtils.random(5, true, true).toLowerCase())
                        .withLabels(new HashMap<String,String>(){{put("app",serviceName);}})
                        .endMetadata()
                        .withNewSpec()
                        .addNewContainer()
                        .withName(serviceName)
                        .withImage(image)
                        .addNewPort()
                        .withContainerPort(Integer.parseInt(port))
                        .endPort()
                        .withImagePullPolicy("Always")
                        .endContainer()
                        .endSpec()
                        .build();
        CoreV1Api api=applicationContext.getBean(KubernetesApiClient.class).getAPI();
        try {
            api.createNamespacedPod("default", pod, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createPodWithSelectedNode(String serviceName, String image, String port,String nodeName) {
        V1Pod pod =
                new V1PodBuilder()
                        .withApiVersion("v1")
                        .withKind("Pod")
                        .withNewMetadata()
                        .withName(serviceName+"-"+ RandomStringUtils.random(10, true, true).toLowerCase()+"-"+RandomStringUtils.random(5, true, true).toLowerCase())
                        .withLabels(new HashMap<String,String>(){{put("app",serviceName);}})
                        .endMetadata()
                        .withNewSpec()
                        .addNewContainer()
                        .withName(serviceName)
                        .withImage(image)
                        .addNewPort()
                        .withContainerPort(Integer.parseInt(port))
                        .endPort()
                        .withImagePullPolicy("Always")
                        .endContainer()
                        .withNodeSelector(new HashMap<String,String>(){{put("kubernetes.io/hostname",nodeName);}})
                        .endSpec()
                        .build();
        CoreV1Api api=applicationContext.getBean(KubernetesApiClient.class).getAPI();
        try {
            api.createNamespacedPod("default", pod, null, null, null);
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePod(String podName) {
        try {
            delete(V1Pod.class).namespace("default").name(podName).execute();
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}