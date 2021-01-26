package mc.Repository;

import mc.Entity.NodeInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NodeRepository extends MongoRepository<NodeInfo, String> {
    Optional<NodeInfo> findByNodeIP(String NodeIP);
}
