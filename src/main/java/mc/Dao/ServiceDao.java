package mc.Dao;

public interface ServiceDao {
    boolean createService(String serviceName, String port);
    boolean deleteService(String serviceName);
}
