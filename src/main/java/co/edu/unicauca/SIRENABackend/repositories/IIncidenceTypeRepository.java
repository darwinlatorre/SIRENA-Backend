package co.edu.unicauca.SIRENABackend.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.common.enums.IncidenceTypeEnum;
import co.edu.unicauca.SIRENABackend.models.IncidenceTypeModel;

public interface IIncidenceTypeRepository extends JpaRepository<IncidenceTypeModel, Integer> {
    public ArrayList<IncidenceTypeModel> findAll();

    public boolean existsByName(IncidenceTypeEnum name);

    public IncidenceTypeModel findByName(IncidenceTypeEnum name);

}
