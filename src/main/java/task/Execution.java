package task;

import lombok.SneakyThrows;
import mc.DTO.ExecutionDTO;
import mc.DTO.ExecutionDetailDTO;
import mc.DTO.NodeInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class Execution  {
    HashMap<String, NodeInfo> nodeMap=new HashMap<>();
    ExecutionDTO executionDTO=new ExecutionDTO();
    public Execution(HashMap<String, NodeInfo> nodeMap,ExecutionDTO executionDTO){
        this.nodeMap=nodeMap;
        this.executionDTO=executionDTO;
    }
    @SneakyThrows
    public void run() {
        for(Map.Entry<String, NodeInfo> entry : nodeMap.entrySet()) {
            StringBuilder command = new StringBuilder("ssh -t ");
            command.append(entry.getKey());
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
