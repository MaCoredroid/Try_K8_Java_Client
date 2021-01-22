package task;

import lombok.SneakyThrows;
import mc.DTO.NodeInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.TimerTask;

public class Execution extends TimerTask {
    HashMap<String, NodeInfo> nodeMap=new HashMap<>();

    @SneakyThrows
    @Override
    public void run() {
        String command="";

        final Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
        new Thread(() -> {
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            try {
                while ((line = input.readLine()) != null) {


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            p.waitFor();
        } catch (Exception ignored) {

        }
    }
}
