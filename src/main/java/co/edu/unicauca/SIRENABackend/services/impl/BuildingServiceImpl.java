package co.edu.unicauca.SIRENABackend.services.impl;

import co.edu.unicauca.SIRENABackend.models.BuildingModel;
import co.edu.unicauca.SIRENABackend.models.FacultyModel;
import co.edu.unicauca.SIRENABackend.repositories.IBuildingRespository;
import co.edu.unicauca.SIRENABackend.repositories.IFacultyRepository;
import co.edu.unicauca.SIRENABackend.services.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private IBuildingRespository buildingReposirtory;

    @Transactional(readOnly = false)
    public BuildingModel saveBuilding(BuildingModel faculty) {
        return buildingReposirtory.save(faculty);
    }

    @Transactional(readOnly = false)
    public boolean deleteBuildingById(Integer id) {
        try {
            buildingReposirtory.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public Optional<BuildingModel> getBuildingById(Integer id) {
        return buildingReposirtory.findById(id);
    }

    @Transactional(readOnly = true)
    public List<BuildingModel> getBuildings() {
        return (List<BuildingModel>) buildingReposirtory.findAll();
    }

}
