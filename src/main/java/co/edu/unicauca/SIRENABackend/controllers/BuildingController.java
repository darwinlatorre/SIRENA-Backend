package co.edu.unicauca.SIRENABackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.BuildingModel;
import co.edu.unicauca.SIRENABackend.services.BuildingService;

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
