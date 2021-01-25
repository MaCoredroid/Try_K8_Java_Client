package mc.Serviceimpl;

import mc.Dao.PodDao;
import mc.Dao.ServiceDao;
import mc.Service.DeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
public class DeployServiceImpl implements DeployService {
    @Autowired
    WebApplicationContext applicationContext;
    @Override
    public ResponseEntity<?> createPodAndService(String serviceName, String image, String port, Integer replicaNum) {
        ServiceDao serviceDao=applicationContext.getBean(ServiceDao.class);
        PodDao podDao=applicationContext.getBean(PodDao.class);
        if(!serviceDao.createService(serviceName, port))
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
}
