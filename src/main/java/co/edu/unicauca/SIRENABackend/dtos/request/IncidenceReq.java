package co.edu.unicauca.SIRENABackend.dtos.request;

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
public class IncidenceReq {
    @JsonProperty("ins_name")
    private String name;
    @JsonProperty("ins_teacher_name")
    private String teacherName;
    @JsonProperty("ins_type")
    private IncidenceTypeEnum incidenceType;
}
