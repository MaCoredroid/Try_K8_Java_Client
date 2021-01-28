package mc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeightDTO {
    public String Node="";
    private String PodIP="";
    private Double Percents=0.0;
    private Double Estimate=0.0;
    private Double NowNodeTop=0.0;
    private Double NowNodeLoad=0.0;
    private Double NowNodePercents=0.0;
    private Integer weight=0;
    private Boolean deprecated=false;
}
