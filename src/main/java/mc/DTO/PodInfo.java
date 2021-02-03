package mc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PodInfo {
    private String PodName="";
    private String PodIP="";
    private String nodeIP="";
    private Double cpu=0.0;
    private Double memory=0.0;
    private Boolean deprecatedFlag=false;
    private Long deprecatedTimestamp=0L;
    private Long startTimestamp=0L;
}
