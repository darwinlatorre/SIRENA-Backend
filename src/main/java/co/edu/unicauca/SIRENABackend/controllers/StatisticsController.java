package co.edu.unicauca.SIRENABackend.controllers;

import co.edu.unicauca.SIRENABackend.models.StatisticsModel;
import co.edu.unicauca.SIRENABackend.services.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que maneja las operaciones relacionadas con las estadisticas.
 */
@RestController
@RequestMapping("/statistics")
@Tag(name = "Statistics controller", description = "Endpoints para las estadisticas")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @Operation(summary = "Obtener las estadisticas de los salones", description = "Obtiene la id de todas las reservas asociadas a un salon")
    @GetMapping("/classrooms")
    public List<StatisticsModel> getClassroomsStatistics() {
        return statisticsService.getClassroomsStatistics();
    }

    @Operation(summary = "Obtener las estadisticas de los edificios", description = "Obtiene la id de todas las reservas asociadas a un edificio")
    @GetMapping("/buildings")
    public List<StatisticsModel> getBuildingsStatistics() {
        return statisticsService.getBuildingsStatistics();
    }

    @Operation(summary = "Obtener las estadisticas de las facultades", description = "Obtiene la id de todas las reservas asociadas a una facultad")
    @GetMapping("/faculties")
    public List<StatisticsModel> getFacultiesStatistics() {
        return statisticsService.getFacultiesStatistics();
    }

    @Operation(summary = "Obtener las estadisticas de los programas", description = "Obtiene la id de todas las reservas asociadas a un programa")
    @GetMapping("/programs")
    public List<StatisticsModel> getProgramsStatistics() {
        return statisticsService.getProgramsStatistics();
    }
}
