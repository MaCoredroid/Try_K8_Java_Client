package mc.Serviceimpl;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import mc.Component.KubernetesApiClient;
import mc.Dao.PodDao;
import mc.Dao.ServiceDao;
import mc.Service.DeployService;
import mc.Task.PullImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

@Service
public class DeployServiceImpl implements DeployService {
    @Autowired
    WebApplicationContext applicationContext;
    @Override
    public ResponseEntity<?> createPodAndService(String serviceName, String image, String port, Integer replicaNum) {
        ServiceDao serviceDao=applicationContext.getBean(ServiceDao.class);
        PodDao podDao=applicationContext.getBean(PodDao.class);
        PullImage pullImage=applicationContext.getBean(PullImage.class);
//        try {
//            pullImage.run(image);
//        }catch (Exception e)
//        {
//            return new ResponseEntity<>("PULL IMAGE ERROR", HttpStatus.SERVICE_UNAVAILABLE);
//        }
        if(!serviceDao.createService(serviceName, port,replicaNum,image))
        {
            return new ResponseEntity<>("SERVICE ERROR", HttpStatus.SERVICE_UNAVAILABLE);
        }
        for(int i=0;i<replicaNum;i++)
        {
            if(!podDao.createPod(serviceName, image, port))
            {
                return new ResponseEntity<>("POD ERROR", HttpStatus.SERVICE_UNAVAILABLE);
            }
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deletePodAndService(String serviceName) {
        ServiceDao serviceDao=applicationContext.getBean(ServiceDao.class);
        PodDao podDao=applicationContext.getBean(PodDao.class);
        if(!serviceDao.deleteService(serviceName))
        {
            return new ResponseEntity<>("DELETE SERVICE ERROR", HttpStatus.SERVICE_UNAVAILABLE);
        }
        CoreV1Api api=applicationContext.getBean(KubernetesApiClient.class).getAPI();
        V1PodList list;
        try {
            list = api.listPodForAllNamespaces(null, null, null, "app="+serviceName, null, null, null, null, null,null);
            for(V1Pod v1Pod:list.getItems())
            {
                podDao.deletePod(Objects.requireNonNull(v1Pod.getMetadata()).getName());
            }
        } catch (ApiException e) {
            e.printStackTrace();
            return new ResponseEntity<>("DELETE POD ERROR", HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
