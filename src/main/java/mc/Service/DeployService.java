package mc.Service;

import org.springframework.http.ResponseEntity;

public interface DeployService {
    ResponseEntity<?> createPodAndService(String serviceName, String image, String port);
}
