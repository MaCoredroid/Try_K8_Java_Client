package mc;/*
Copyright 2020 The Kubernetes Authors.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceList;
import mc.Component.KubernetesApiClient;
import mc.DTO.NodeInfo;
import mc.DTO.ServiceInfo;
import mc.Task.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A simple example of how to use the Java API inside a kubernetes cluster
 *
 * <p>Easiest way to run this: mvn exec:java
 * -Dexec.mainClass="io.kubernetes.client.examples.mc.InClusterClientExample"
 *
 * <p>From inside $REPO_DIR/examples
 */
@SpringBootApplication
public class App {
    @Autowired
    WebApplicationContext applicationContext;
    public static void main(String[] args) throws IOException, ApiException {

        ConfigurableApplicationContext run=SpringApplication.run(App.class, args);
        App app = run.getBean(App.class);
        app.run();
        
    }
    private void run(){
        Runnable r1 = () -> {
            CoreV1Api api=applicationContext.getBean(KubernetesApiClient.class).getAPI();
            ApiClient client=applicationContext.getBean(KubernetesApiClient.class).getClient();
            HashMap<String, ServiceInfo> serviceNameMap = new HashMap<>();
            HashMap<String, NodeInfo> nodeMap=new HashMap<>();
//        // invokes the CoreV1Api client
            Timer t = new Timer();
            CheckNodeStatus checkNodeStatus =new CheckNodeStatus(api,nodeMap);
            CheckNodeList checkNodeList=new CheckNodeList(api,nodeMap);
            CheckPodStatus checkPodStatus=new CheckPodStatus(api,serviceNameMap);
            CheckPodAndNodeUsage checkPodAndNodeUsage =new CheckPodAndNodeUsage(client,nodeMap,serviceNameMap);
            Calculate calculate=new Calculate(serviceNameMap, nodeMap);
            V1ServiceList serviceList = null;
            try {
                serviceList = api.listServiceForAllNamespaces(null, null, null, null, null, null, null, null, null,null);
            } catch (ApiException e) {
                e.printStackTrace();
            }
            for (V1Service item : Objects.requireNonNull(serviceList).getItems()) {
                if (Objects.equals(Objects.requireNonNull(item.getMetadata()).getNamespace(), "default")&&Objects.equals(Objects.requireNonNull(item.getMetadata()).getName(), "application")) {
                    serviceNameMap.put(item.getMetadata().getName(),new ServiceInfo(item.getMetadata().getName(), Objects.requireNonNull(item.getSpec()).getClusterIP(),new HashMap<>()));
                }
            }
            t.scheduleAtFixedRate(checkNodeStatus, 0, 500);
            t.scheduleAtFixedRate(checkPodAndNodeUsage, 0, 500);
            t.scheduleAtFixedRate(checkNodeList, 0, 5000);
            t.scheduleAtFixedRate(checkPodStatus, 0, 5000);
            t.scheduleAtFixedRate(calculate, 0, 1000);
        };

        //Create an executor service with 2 threads (it can be like 50
        //if you need it to be).  Submit our two tasks to it and they'll
        //both run to completion (or forever if they don't end).
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(r1);

        //Wait or completion of tasks (or forever).
        service.shutdown();
        try { service.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }

}
