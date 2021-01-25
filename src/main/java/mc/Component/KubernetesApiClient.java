package mc.Component;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;

@Component
public class KubernetesApiClient {
    private final CoreV1Api api;
    private final io.kubernetes.client.openapi.ApiClient client;
    public KubernetesApiClient()
    {
        String kubeConfigPath = System.getProperty("user.home") + "/.kube/config";
        io.kubernetes.client.openapi.ApiClient client =
                null;
        try {
            client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // if you prefer not to refresh service account token, please use:
        // ApiClient client = ClientBuilder.oldCluster().build();

        // set the global default api-client to the in-cluster one from above
        Configuration.setDefaultApiClient(client);
        this.client=client;
        // the CoreV1Api loads default api-client from global configuration.
        this.api = new CoreV1Api();
    }

    public CoreV1Api getAPI(){
        return this.api;
    }

    public ApiClient getClient(){
        return this.client;
    }
}
