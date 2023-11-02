package co.edu.unicauca.SIRENABackend.security.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import co.edu.unicauca.SIRENABackend.security.dtos.request.RoleReq;
import co.edu.unicauca.SIRENABackend.security.dtos.response.RoleRes;

public interface RoleService {
    ArrayList<RoleRes> getRoles();

    Optional<RoleRes> getByRoleName(RoleEnum prmRoleName);

    RoleRes saveRole(RoleReq prmRole);

    Optional<RoleRes> getRoleById(Integer prmId);

}
