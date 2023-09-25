package co.edu.unicauca.SIRENABackend.Auth;

import co.edu.unicauca.SIRENABackend.models.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    Integer usr_id;
    RoleModel usr_role;
    String usr_firstname;
    String usr_lastname;
    String usr_name; 
    String usr_password;
    String usr_email;
}