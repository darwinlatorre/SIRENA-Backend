package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.RoleModel;

public interface IRoleRepository extends JpaRepository<RoleModel, Integer>{
    
}
