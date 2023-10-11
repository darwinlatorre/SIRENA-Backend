package co.edu.unicauca.SIRENABackend.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterReq {
    /**
     * Identificador del usuario.
     */
    protected Integer usr_id;

    /**
     * Nombre del usuario.
     */
    protected String usr_name;

    /**
     * Primer nombre del usuario.
     */
    protected String usr_firstname;

    /**
     * Apellido del usuario.
     */
    protected String usr_lastname;

    /**
     * Correo electrónico del usuario.
     */
    protected String usr_email;
    
    /**
     * Contraseña del usuario.
     */
    protected String usr_password;

    /**
     * Rol del usuario.
     */
    protected String usr_role;
}