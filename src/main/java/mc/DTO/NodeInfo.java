package mc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeInfo {
    String nodeIP="";
    Double node_memory_MemTotal_bytes=0.0;
    Double node_memory_MemAvailable_bytes=0.0;
    Double node_load_1m=0.0;
}
