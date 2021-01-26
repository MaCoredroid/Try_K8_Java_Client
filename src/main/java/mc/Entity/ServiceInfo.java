package mc.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mc.DTO.PodInfo;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ServiceInfo")
public class ServiceInfo {
    @Id
    private String serviceName;
    private String clusterIP="";
    private HashMap<String, PodInfo> pods=new HashMap<>();
}
