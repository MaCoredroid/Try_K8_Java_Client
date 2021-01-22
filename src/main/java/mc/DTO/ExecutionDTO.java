package mc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionDTO {
    String serviceIP="";
    List<ExecutionDetailDTO> executionDetailDTOS=new ArrayList<>();
}
