package mc.Controller;

import mc.DTO.DeployDTO;
import mc.Service.DeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

@RestController
@RequestMapping("/deploy")
public class DeployController {
    @Autowired
    WebApplicationContext applicationContext;
    @RequestMapping(value = "/deployApplication", method = { RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> deployApplication(@RequestBody DeployDTO deployDTO)  {
        return applicationContext.getBean(DeployService.class).createPodAndService(deployDTO.getServiceName(),deployDTO.getImage(), deployDTO.getPort(), Integer.parseInt(deployDTO.getReplicaNum()));
    }
}
