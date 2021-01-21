package task;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TimerTask;

public class CheckPodUsage extends TimerTask {
    public CheckPodUsage()
    {

    }
    @SneakyThrows
    @Override
    public void run() {
        String command="kubectl top pods";

        final Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
        new Thread(() -> {
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            try {
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
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
