package co.edu.unicauca.SIRENABackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.dtos.request.FacultyRequest;
import co.edu.unicauca.SIRENABackend.models.BuildingModel;
import co.edu.unicauca.SIRENABackend.models.FacultyModel;
import co.edu.unicauca.SIRENABackend.services.BuildingService;
import co.edu.unicauca.SIRENABackend.services.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private BuildingService buildingService;

    @PostMapping
    public ResponseEntity<FacultyModel> saveFaculty(@RequestBody FacultyRequest facultyRequest) {
        // Busca el edificio por su id
        Optional<BuildingModel> buildingOptional = buildingService.getBuildingById(facultyRequest.getBuildingId());

        if (buildingOptional.isPresent()) {
            BuildingModel building = buildingOptional.get();

            // Crea la nueva facultad
            FacultyModel faculty = new FacultyModel();
            faculty.setName(facultyRequest.getName());
            faculty.setBuilding(building);

            // Guarda la facultad
            FacultyModel savedFaculty = facultyService.saveFaculties(faculty);

            return new ResponseEntity<>(savedFaculty, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<FacultyModel> getFaculties() {
        return facultyService.getFaculties();
    }

    @GetMapping("/{id}")
    public Optional<FacultyModel> getFacultyById(@PathVariable Integer id) {
        return facultyService.getFacultyById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteFacultyById(@PathVariable Integer id) {
        return facultyService.deleteFacultyById(id);
    }
}
