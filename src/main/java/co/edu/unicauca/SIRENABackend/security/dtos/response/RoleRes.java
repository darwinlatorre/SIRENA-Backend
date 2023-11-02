package co.edu.unicauca.SIRENABackend.security.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRes {

    @JsonProperty("rol_id")
    private Integer id;

    @JsonProperty("rol_name")
    private RoleEnum name;
}
