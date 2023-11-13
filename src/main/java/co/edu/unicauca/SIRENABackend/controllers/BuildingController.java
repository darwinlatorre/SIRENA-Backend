package co.edu.unicauca.SIRENABackend.controllers;

import co.edu.unicauca.SIRENABackend.models.BuildingModel;
import co.edu.unicauca.SIRENABackend.models.FacultyModel;
import co.edu.unicauca.SIRENABackend.services.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @PostMapping
    public BuildingModel saveBuilding(@RequestBody BuildingModel building) {
        System.out.println(building.toString());
        return buildingService.saveBuilding(building);
    }

    @GetMapping
    public List<BuildingModel> getBuilding() {
        return buildingService.getBuildings();
    }

    @GetMapping("/{id}")
    public Optional<BuildingModel> getBuildingById(@PathVariable Integer id) {
        return buildingService.getBuildingById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBuildingById(@PathVariable Integer id) {
        return buildingService.deleteBuildingById(id);
    }
}
