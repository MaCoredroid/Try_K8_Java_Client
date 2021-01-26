package mc.Task;

import mc.DTO.ExecutionDTO;
import mc.DTO.ExecutionDetailDTO;
import mc.Entity.NodeInfo;
import mc.Repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
@Component
public class Execution  {

    @Autowired
    WebApplicationContext applicationContext;

    @Async
    public void run(ExecutionDTO executionDTO) throws IOException {
        NodeRepository nodeRepository=applicationContext.getBean(NodeRepository.class);
        List<NodeInfo> nodeInfos=nodeRepository.findAll();
        for(NodeInfo nodeInfo:nodeInfos) {
            StringBuilder command = new StringBuilder("ssh -t ");
            command.append(nodeInfo.getNodeIP());
            command.append(" \"");
            String ServiceIP=executionDTO.getServiceIP();
            for(ExecutionDetailDTO executionDetailDTO:executionDTO.getExecutionDetailDTOS())
            {
                command.append("sudo ipvsadm -e -t ").append(ServiceIP).append(":8080 -r ").append(executionDetailDTO.getPodIP()).append(":8080 -m -w 2").append(" && ");
            }
            String execute=command.substring(0, command.length()-4)+"\"";
            final Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", execute});
            new Thread(() -> {
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            }).start();
            try {
                p.waitFor();
            } catch (Exception ignored) {

            }
        }
    }
}
