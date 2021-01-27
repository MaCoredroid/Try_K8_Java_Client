package mc.Dao;

public interface ServiceDao {
    boolean createService(String serviceName, String port,Integer desiredReplicaNum, String image);
    boolean deleteService(String serviceName);
}
