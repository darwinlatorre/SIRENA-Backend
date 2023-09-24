package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.InsidenciasModel;

public interface IInsidenciasRepository extends JpaRepository<InsidenciasModel, Integer>{
    
}
