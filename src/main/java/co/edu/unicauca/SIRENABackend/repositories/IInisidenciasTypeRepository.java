package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.InsidenciasTypeModel;

public interface IInisidenciasTypeRepository extends JpaRepository<InsidenciasTypeModel, Integer>{
    
}
