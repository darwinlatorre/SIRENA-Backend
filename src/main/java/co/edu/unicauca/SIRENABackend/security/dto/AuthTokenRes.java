package co.edu.unicauca.SIRENABackend.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenRes {
    /**
     * Token de autenticación generado para el usuario.
     */
    @JsonProperty("acces_token")
    private String accesToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
