package mc.Task;

import mc.Entity.NodeInfo;
import mc.Repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class PullImage {
    @Autowired
    WebApplicationContext applicationContext;


    public void run(String image) throws IOException {
        NodeRepository nodeRepository=applicationContext.getBean(NodeRepository.class);
        List<NodeInfo> nodeInfos=nodeRepository.findAll();
        for(NodeInfo nodeInfo:nodeInfos) {


            String command = "ssh -t " + nodeInfo.getNodeIP() +
                    " \"" +
                    "sudo docker pull " + image;
            final Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
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
