package mc.Controller;

import mc.DTO.DeployDTO;
import mc.Service.DeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@RestController
@RequestMapping("/deploy")
public class DeployController {
    @Autowired
    WebApplicationContext applicationContext;
    @RequestMapping(value = "/deployApplication", method = { RequestMethod.PUT, RequestMethod.OPTIONS }, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> deployApplication(@RequestBody DeployDTO deployDTO)  {
        return applicationContext.getBean(DeployService.class).createPodAndService(deployDTO.getServiceName(),deployDTO.getImage(), deployDTO.getPort(), Integer.parseInt(deployDTO.getReplicaNum()));
    }

    @RequestMapping(value = "/deleteApplication/{serviceName}", method = { RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> deleteApplication(@PathVariable("serviceName") String serviceName)  {
        return applicationContext.getBean(DeployService.class).deletePodAndService(serviceName);
    }
}
