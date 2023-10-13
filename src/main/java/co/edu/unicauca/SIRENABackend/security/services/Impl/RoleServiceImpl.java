package co.edu.unicauca.SIRENABackend.security.services.Impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.security.models.RoleModel;
import co.edu.unicauca.SIRENABackend.security.repositories.IRoleRepository;

@Service
public class RoleServiceImpl {
    @Autowired
    IRoleRepository roleRepository;

    @Transactional(readOnly = true)
    public ArrayList<RoleModel> getRoles() {
        return (ArrayList<RoleModel>) roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<RoleModel> getByRoleName(String prmRoleName) {
        return roleRepository.findByName(prmRoleName);
    }

    @Transactional
    public RoleModel saveRole(RoleModel prmRole) {
        return roleRepository.save(prmRole);
    }

    @Transactional(readOnly = true)
    public Optional<RoleModel> getRoleById(Integer prmId) {
        return roleRepository.findById(prmId);
    }

    @Transactional
    public boolean deleteRoleById(Integer prmId) {
        try {
            roleRepository.deleteById(prmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
