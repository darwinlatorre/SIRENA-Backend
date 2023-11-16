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

/**
 * Controlador REST que maneja las operaciones relacionadas con los edificios (buildings).
 */
@RestController
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    /**
     * Guarda un nuevo edificio.
     *
     * @param building El objeto BuildingModel que se desea guardar.
     * @return El objeto BuildingModel guardado.
     */
    @PostMapping
    public BuildingModel saveBuilding(@RequestBody BuildingModel building) {
        return buildingService.saveBuilding(building);
    }

    /**
     * Obtiene todos los edificios almacenados en la base de datos.
     *
     * @return Una lista de objetos BuildingModel.
     */
    @GetMapping
    public List<BuildingModel> getBuilding() {
        return buildingService.getBuildings();
    }

    /**
     * Obtiene un edificio por su ID.
     *
     * @param id El identificador único del edificio que se desea obtener.
     * @return Un objeto Optional que contiene el BuildingModel encontrado (si existe).
     */
    @GetMapping("/{id}")
    public Optional<BuildingModel> getBuildingById(@PathVariable Integer id) {
        return buildingService.getBuildingById(id);
    }

    /**
     * Elimina un edificio por su ID.
     *
     * @param id El identificador único del edificio que se desea eliminar.
     * @return `true` si el edificio se eliminó con éxito, `false` si no se encuentra.
     */
    @DeleteMapping("/{id}")
    public boolean deleteBuildingById(@PathVariable Integer id) {
        return buildingService.deleteBuildingById(id);
    }
}
