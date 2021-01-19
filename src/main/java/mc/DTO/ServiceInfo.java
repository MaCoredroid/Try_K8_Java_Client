package mc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInfo {
    private String serviceName="";
    private String clusterIP="";
    private List<PodInfo> podInfos=new ArrayList<>();

}
