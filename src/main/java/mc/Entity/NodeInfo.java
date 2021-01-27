package mc.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "NodeInfo")
public class NodeInfo {
    @Id
    String id="";//node name
    Boolean applicable=true;//can be scheduled
    String nodeIP="";
    Double node_memory_MemTotal_bytes=0.0;
    Double node_memory_MemAvailable_bytes=0.0;
    Double node_memory_percents=0.0;
    Double node_load_cpu_1m=0.0;
    Double node_cpu_total=0.0;
    Double node_top_cpu_value =0.0;
    Double node_top_cpu_percents =0.0;
    Double node_load_cpu_percents =0.0;
    Double total_time=0.0;
    Double cpu_idle_time=0.0;
    Double cpu_idle_percent=0.0;
}
