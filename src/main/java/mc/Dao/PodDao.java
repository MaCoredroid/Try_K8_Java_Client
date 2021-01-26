package mc.Dao;

public interface PodDao {
    boolean createPod(String serviceName, String image, String port);
    boolean createPodWithSelectedNode(String serviceName, String image, String port,String nodeName);
    boolean deletePod(String podName);
}
