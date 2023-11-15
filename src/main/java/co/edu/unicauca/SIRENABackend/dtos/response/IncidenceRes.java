package co.edu.unicauca.SIRENABackend.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidenceRes {
    @JsonProperty("ins_id")
    private Integer id;
    @JsonProperty("ins_name")
    private String name;
    @JsonProperty("ins_teacher_name")
    private String teacherName;
    @JsonProperty("ins_type")
    private IncidenceTypeModel incidenceType;
}
