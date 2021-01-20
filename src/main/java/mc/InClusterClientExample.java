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
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import mc.DTO.NodeInfo;
import mc.DTO.PodInfo;
import mc.DTO.ServiceInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

/**
 * A simple example of how to use the Java API inside a kubernetes cluster
 *
 * <p>Easiest way to run this: mvn exec:java
 * -Dexec.mainClass="io.kubernetes.client.examples.mc.InClusterClientExample"
 *
 * <p>From inside $REPO_DIR/examples
 */
public class InClusterClientExample {
    public static void main(String[] args) throws IOException, ApiException {

        // loading the in-cluster config, including:
        //   1. service-account CA
        //   2. service-account bearer-token
        //   3. service-account namespace
        //   4. master endpoints(ip, port) from pre-set environment variables
        String kubeConfigPath = System.getProperty("user.home") + "/.kube/config";
        ApiClient client =
                ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();

        // if you prefer not to refresh service account token, please use:
        // ApiClient client = ClientBuilder.oldCluster().build();

        // set the global default api-client to the in-cluster one from above
        Configuration.setDefaultApiClient(client);

        // the CoreV1Api loads default api-client from global configuration.
        CoreV1Api api = new CoreV1Api();

        HashMap<String,ServiceInfo> serviceNameMap = new HashMap<>();
        HashMap<String, NodeInfo> nodeMap=new HashMap<>();
        // invokes the CoreV1Api client
        V1ServiceList serviceList = api.listServiceForAllNamespaces(null, null, null, null, null, null, null, null, null);
        for (V1Service item : serviceList.getItems()) {
            if (Objects.equals(Objects.requireNonNull(item.getMetadata()).getNamespace(), "default")&&Objects.equals(Objects.requireNonNull(item.getMetadata()).getName(), "application")) {
                serviceNameMap.put(item.getMetadata().getName(),new ServiceInfo(item.getMetadata().getName(), Objects.requireNonNull(item.getSpec()).getClusterIP(),new ArrayList<>()));
            }


        }
        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        for (V1Pod item : list.getItems()) {
            if (Objects.equals(Objects.requireNonNull(item.getMetadata()).getNamespace(), "default")) {
                String[] arrOfStr = Objects.requireNonNull(Objects.requireNonNull(item.getMetadata()).getName()).split("-", 2);
                String serviceName=arrOfStr[0];
                if(!serviceNameMap.containsKey(serviceName))
                {
                    continue;
                }
                serviceNameMap.get(serviceName).getPodInfos().add(new PodInfo(Objects.requireNonNull(item.getMetadata()).getName(),Objects.requireNonNull(item.getStatus()).getPodIP(),Objects.requireNonNull(item.getStatus()).getHostIP()));
            }

        }
        System.out.println(serviceNameMap);

        List<PodInfo> pods=serviceNameMap.getOrDefault("application",new ServiceInfo()).getPodInfos();


        for(PodInfo podInfo:pods) {
            NodeInfo nodeInfo=new NodeInfo();
            String nodeIP=podInfo.getNodeIP();
            nodeInfo.setNodeIP(nodeIP);
            String command="curl http://"+nodeIP+":9100/metrics | grep 'node_memory_MemTotal_bytes\\|node_memory_MemAvailable_bytes'";
            final Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
            new Thread(() -> {
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;

                try {
                    while ((line = input.readLine()) != null) {
                        if(!line.startsWith("#")) {
                            if(line.startsWith("node_memory_MemTotal_bytes"))
                            {
                                Double value= Double.parseDouble(line.replace("node_memory_MemTotal_bytes","").trim());
                                nodeInfo.setNode_memory_MemTotal_bytes(value);
                            }
                            else if(line.startsWith("node_memory_MemAvailable_bytes"))
                            {
                                Double value= Double.parseDouble(line.replace("node_memory_MemAvailable_bytes","").trim());
                                nodeInfo.setNode_memory_MemAvailable_bytes(value);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            nodeMap.put(nodeIP,nodeInfo);
            try {
                p.waitFor();
            } catch (Exception ignored) {

            }
        }
        System.out.println(nodeMap);

        
    }
}
