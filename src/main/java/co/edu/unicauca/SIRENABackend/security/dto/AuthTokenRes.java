package co.edu.unicauca.SIRENABackend.security.dto;

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
    String token; 
}
