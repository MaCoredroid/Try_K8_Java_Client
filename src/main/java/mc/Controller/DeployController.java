package mc.Controller;

import mc.DTO.DeployDTO;
import mc.Service.DeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
