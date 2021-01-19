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

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

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

    // invokes the CoreV1Api client
    V1PodList list =
        api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
    for (V1Pod item : list.getItems()) {
      if(Objects.equals(Objects.requireNonNull(item.getMetadata()).getNamespace(), "default")) {
        System.out.println(Objects.requireNonNull(item.getMetadata()).getName());
        System.out.println(Objects.requireNonNull(item.getStatus()).getHostIP());
        System.out.println(Objects.requireNonNull(item.getStatus()).getPodIP());
      }

    }
     V1ServiceList serviceList=api.listServiceForAllNamespaces(null,null,null,null,null,null,null,null,null);
    for (V1Service item : serviceList.getItems()) {
      if(Objects.equals(Objects.requireNonNull(item.getMetadata()).getNamespace(), "default")) {
        System.out.println(Objects.requireNonNull(item.getMetadata()).getName());
        System.out.println(Objects.requireNonNull(item.getSpec()).getClusterIP());
      }


    }
  }
}
