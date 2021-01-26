package mc.Repository;

import mc.Entity.ServiceInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceRepository extends MongoRepository<ServiceInfo, String> {

}
