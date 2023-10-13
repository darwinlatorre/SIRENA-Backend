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

    protected Integer usr_id;

    protected String usr_name;

    protected String usr_firstname;

    protected String usr_lastname;

    protected String usr_email;
    
    protected String usr_password;

    protected String usr_role;
}