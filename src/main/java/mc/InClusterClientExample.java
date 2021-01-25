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

import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.monitoring.Monitoring;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import io.kubernetes.client.util.Yaml;
import mc.DTO.NodeInfo;
import mc.DTO.ServiceInfo;
import task.*;

import java.io.FileReader;
import java.io.IOException;
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
//        CoreV1Api api = new CoreV1Api();
//        HashMap<String,ServiceInfo> serviceNameMap = new HashMap<>();
//        HashMap<String, NodeInfo> nodeMap=new HashMap<>();
//        // invokes the CoreV1Api client
//        Timer t = new Timer();
//        CheckNodeStatus checkNodeStatus =new CheckNodeStatus(api,nodeMap);
//        CheckNodeList checkNodeList=new CheckNodeList(api,nodeMap);
//        CheckPodStatus checkPodStatus=new CheckPodStatus(api,serviceNameMap);
//        CheckPodAndNodeUsage checkPodAndNodeUsage =new CheckPodAndNodeUsage(client,nodeMap,serviceNameMap);
//        Calculate calculate=new Calculate(serviceNameMap, nodeMap);
//        V1ServiceList serviceList = api.listServiceForAllNamespaces(null, null, null, null, null, null, null, null, null);
//        for (V1Service item : serviceList.getItems()) {
//            if (Objects.equals(Objects.requireNonNull(item.getMetadata()).getNamespace(), "default")&&Objects.equals(Objects.requireNonNull(item.getMetadata()).getName(), "application")) {
//                serviceNameMap.put(item.getMetadata().getName(),new ServiceInfo(item.getMetadata().getName(), Objects.requireNonNull(item.getSpec()).getClusterIP(),new HashMap<>()));
//            }
//        }
//        t.scheduleAtFixedRate(checkNodeStatus, 0, 500);
//        t.scheduleAtFixedRate(checkPodAndNodeUsage, 0, 500);
//        t.scheduleAtFixedRate(checkNodeList, 0, 5000);
//        t.scheduleAtFixedRate(checkPodStatus, 0, 5000);
//        t.scheduleAtFixedRate(calculate, 0, 1000);
        V1Pod pod =
                new V1PodBuilder()
                        .withNewMetadata()
                        .withLabels(new HashMap<String,String>(){{put("app","application");}})
                        .endMetadata()
                        .withNewSpec()
                        .addNewContainer()
                        .withName("application")
                        .withImage("registry.cn-shanghai.aliyuncs.com/macoredroid/perftest:2.3")
                        .addNewPort()
                        .withContainerPort(8080)
                        .endPort()
                        .withImagePullPolicy("Always")
                        .endContainer()
                        .endSpec()
                        .build();
        System.out.println(Yaml.dump(pod));

        V1Service svc =
                new V1ServiceBuilder()
                        .withNewMetadata()
                        .withName("application")
                        .withLabels(new HashMap<String,String>(){{put("app","application");}})
                        .endMetadata()
                        .withNewSpec()
                        .addNewPort()
                        .withName("http")
                        .withPort(8008)
                        .endPort()
                        .withSelector(new HashMap<String,String>(){{put("app","application");}})
                        .endSpec()
                        .build();
        System.out.println(Yaml.dump(svc));




        
    }
}
