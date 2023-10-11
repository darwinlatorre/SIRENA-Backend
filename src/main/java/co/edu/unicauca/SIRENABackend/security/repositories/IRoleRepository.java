package co.edu.unicauca.SIRENABackend.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.security.models.RoleModel;

/**
 * Interfaz que define un repositorio para operaciones relacionadas con el rol del usuario.
 */
public interface IRoleRepository extends JpaRepository<RoleModel, Integer>{
    Optional<RoleModel> findByName(String prmName);
    
}
