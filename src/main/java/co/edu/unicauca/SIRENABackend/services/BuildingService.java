package co.edu.unicauca.SIRENABackend.services;

import co.edu.unicauca.SIRENABackend.models.BuildingModel;

import java.util.List;
import java.util.Optional;

public interface BuildingService {
    List<BuildingModel> getBuildings();

    BuildingModel saveBuilding(BuildingModel prmIncidence);

    Optional<BuildingModel> getBuildingById(Integer prmId);

    boolean deleteBuildingById(Integer prmId);
}
