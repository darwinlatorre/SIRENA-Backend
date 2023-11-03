package co.edu.unicauca.SIRENABackend.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.common.enums.IncidenceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidenceTypeRes {
    @JsonProperty("ins_type_id")
    private Integer id;

    @JsonProperty("ins_type_name")
    private IncidenceTypeEnum name;
}
