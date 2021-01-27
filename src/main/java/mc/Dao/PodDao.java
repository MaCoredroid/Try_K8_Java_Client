package mc.Dao;

public interface PodDao {
    boolean createPod(String serviceName, String image, String port);
    boolean createPodWithSelectedNode(String serviceName, String image, String port,String nodeName);
    void deletePod(String podName);
    boolean migratePod(String podName,String serviceName);
}
