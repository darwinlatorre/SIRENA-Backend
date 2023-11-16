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

/**
 * Controlador REST que maneja las operaciones relacionadas con las facultades (faculties).
 * Proporciona endpoints para la creación, recuperación y eliminación de facultades.
 */
@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private BuildingService buildingService;

    /**
     * Crea una nueva facultad.
     *
     * @param facultyRequest El objeto FacultyRequest que contiene la información de la nueva facultad.
     * @return Una respuesta HTTP con el objeto FacultyModel creado y el código de estado 201 (CREATED).
     *         Si no se encuentra el edificio asociado, devuelve un código de estado 404 (NOT FOUND).
     */
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

    /**
     * Obtiene una lista de todas las facultades.
     *
     * @return Una lista de objetos FacultyModel y el código de estado 200 (OK).
     */
    @GetMapping
    public List<FacultyModel> getFaculties() {
        return facultyService.getFaculties();
    }

    /**
     * Obtiene una facultad por su ID.
     *
     * @param id El identificador único de la facultad que se desea obtener.
     * @return Un objeto Optional que contiene el FacultyModel encontrado (si existe).
     */
    @GetMapping("/{id}")
    public Optional<FacultyModel> getFacultyById(@PathVariable Integer id) {
        return facultyService.getFacultyById(id);
    }

    /**
     * Elimina una facultad por su ID.
     *
     * @param id El identificador único de la facultad que se desea eliminar.
     * @return `true` si la facultad se eliminó con éxito, `false` si no se encuentra.
     */
    @DeleteMapping("/{id}")
    public boolean deleteFacultyById(@PathVariable Integer id) {
        return facultyService.deleteFacultyById(id);
    }
}
