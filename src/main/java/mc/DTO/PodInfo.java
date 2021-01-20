package mc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PodInfo {
    private String PodName="";
    private String PodIP="";
    private String nodeIP="";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PodInfo podInfo = (PodInfo) o;
        return Objects.equals(PodName, podInfo.PodName) &&
                Objects.equals(PodIP, podInfo.PodIP) &&
                Objects.equals(nodeIP, podInfo.nodeIP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PodName, PodIP, nodeIP);
    }
}
