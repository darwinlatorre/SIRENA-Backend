package co.edu.unicauca.SIRENABackend.repositories;

import co.edu.unicauca.SIRENABackend.models.BuildingModel;
import co.edu.unicauca.SIRENABackend.models.IncidenceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBuildingRespository extends JpaRepository<BuildingModel, Integer> {
}
