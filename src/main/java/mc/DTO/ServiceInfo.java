package mc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInfo {
    private String serviceName="";
    private String clusterIP="";
    private HashMap<String,PodInfo> pods=new HashMap<>();
}
