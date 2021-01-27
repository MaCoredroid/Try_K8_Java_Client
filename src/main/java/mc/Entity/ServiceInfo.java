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
    private String id="";//service name
    private String clusterIP="";
    private Integer desiredReplicaNum=0;
    private Boolean migrationFlag=false;
    private String image="";
    private String port="";
    private Long timestamp=0L;
    private HashMap<String, PodInfo> pods=new HashMap<>();
}
