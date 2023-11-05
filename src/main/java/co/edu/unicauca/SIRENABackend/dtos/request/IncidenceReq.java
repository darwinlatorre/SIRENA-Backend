package co.edu.unicauca.SIRENABackend.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.common.enums.IncidenceTypeEnum;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidenceReq {
    @JsonProperty("ins_id")
    private Integer id;
    @JsonProperty("ins_name")
    private String name;
    @JsonProperty("ins_teacher_name")
    private UserModel teacher;
    @JsonProperty("ins_type")
    private IncidenceTypeEnum incidenceType;
}
