package co.edu.unicauca.SIRENABackend.security.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.security.models.RoleModel;

public interface RoleService {
    ArrayList<RoleModel> getRoles();

    Optional<RoleModel> getByRoleName(String prmRoleName);

    RoleModel saveRole(RoleModel prmRole);

    Optional<RoleModel> getRoleById(Integer prmId);

    boolean deleteRoleById(Integer prmId);
}
