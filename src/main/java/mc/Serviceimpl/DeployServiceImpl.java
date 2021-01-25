package mc.Serviceimpl;

import mc.Service.DeployService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeployServiceImpl implements DeployService {
    @Override
    public ResponseEntity<?> createPodAndService(String serviceName, String image, String port) {
        return null;
    }
}
