package mc.Dao;

public interface PodDao {
    boolean createPod(String serviceName, String image, String port);
}
