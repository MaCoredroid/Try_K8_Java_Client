package mc.Daoimpl;

import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1EnvVarSource;
import io.kubernetes.client.openapi.models.V1ObjectFieldSelector;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodBuilder;
import mc.Component.KubernetesApiClient;
import mc.Dao.PodDao;
import mc.Entity.ServiceInfo;
import mc.Repository.ServiceRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;
import java.util.HashMap;

import static io.kubernetes.client.extended.kubectl.Kubectl.delete;

@Repository
public class PodDaoImpl implements PodDao {
    @Autowired
    WebApplicationContext applicationContext;
    @Override
    public boolean createPod(String serviceName, String image, String port) {
        String podName=serviceName+"-"+ RandomStringUtils.random(10, true, true).toLowerCase()+"-"+RandomStringUtils.random(5, true, true).toLowerCase();
        V1EnvVarSource source=new V1EnvVarSource();
        V1ObjectFieldSelector fieldSelector=new V1ObjectFieldSelector();
        fieldSelector.setFieldPath("metadata.name");
        source.setFieldRef(fieldSelector);
        V1Pod pod =
        new V1PodBuilder()
                        .withApiVersion("v1")
                        .withKind("Pod")
                        .withNewMetadata()
                        .withName(podName)
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
                        .addNewEnv()
                        .withName("Pod_Name")
                        .withValueFrom(source)
                        .endEnv()
                        .endContainer()
                        .endSpec()
                        .build();
        CoreV1Api api=applicationContext.getBean(KubernetesApiClient.class).getAPI();
//        System.out.println(Yaml.dump(pod));
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
        String podName=serviceName+"-"+ RandomStringUtils.random(10, true, true).toLowerCase()+"-"+RandomStringUtils.random(5, true, true).toLowerCase();
        V1EnvVarSource source=new V1EnvVarSource();
        V1ObjectFieldSelector fieldSelector=new V1ObjectFieldSelector();
        fieldSelector.setFieldPath("metadata.name");
        source.setFieldRef(fieldSelector);
        V1Pod pod =
                new V1PodBuilder()
                        .withApiVersion("v1")
                        .withKind("Pod")
                        .withNewMetadata()
                        .withName(podName)
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
                        .addNewEnv()
                        .withName("Pod_Name")
                        .withValueFrom(source)
                        .endEnv()
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
    public void deletePod(String podName) {
        try {
            delete(V1Pod.class).namespace("default").name(podName).execute();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean migratePod(String podName, String serviceName) {
        ServiceRepository serviceRepository=applicationContext.getBean(ServiceRepository.class);
        if(serviceRepository.findById(serviceName).isPresent())
        {
            ServiceInfo serviceInfo=serviceRepository.findById(serviceName).get();
            if(!serviceInfo.getPods().containsKey(podName))
            {
                return false;
            }
            if(!createPod(serviceName,serviceInfo.getImage(),serviceInfo.getPort()))
            {
                return false;
            }
            serviceInfo.setDesiredReplicaNum(serviceInfo.getDesiredReplicaNum()+1);
            serviceInfo.setTimestamp(Instant.now().toEpochMilli());
            serviceInfo.setMigrationFlag(true);
            serviceInfo.getPods().get(podName).setDeprecatedFlag(true);
            serviceRepository.save(serviceInfo);
        }
        return true;
    }
}
