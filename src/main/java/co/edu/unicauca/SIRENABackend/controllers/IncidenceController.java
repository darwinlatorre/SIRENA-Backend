package co.edu.unicauca.SIRENABackend.controllers;

import java.util.ArrayList;
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

import co.edu.unicauca.SIRENABackend.dtos.request.IncidenceReq;
import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceRes;
import co.edu.unicauca.SIRENABackend.services.IncidenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/incidence")
public class IncidenceController {
    @Autowired
    IncidenceService incidenceService;

    @Operation(summary = "Obtener incidencias", description = "Obtiene una lista de incidencias.", responses = {
            @ApiResponse(responseCode = "200", description = "Incidencias encontradas exitosamente", content = @Content(schema = @Schema(implementation = IncidenceRes.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron incidencias")
    })
    @GetMapping()
    public ResponseEntity<ArrayList<IncidenceRes>> getIncidences() {
        ArrayList<IncidenceRes> incidences = incidenceService.getIncidences();

        if (!incidences.isEmpty()) {
            return new ResponseEntity<>(incidences, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Guardar incidencia", description = "Guarda una nueva incidencia.", responses = {
            @ApiResponse(responseCode = "201", description = "Incidencia guardada exitosamente", content = @Content(schema = @Schema(implementation = IncidenceRes.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping()
    public ResponseEntity<IncidenceRes> saveIncidence(@RequestBody IncidenceReq prmIncidence) {
        IncidenceRes savedIncidence = this.incidenceService.saveIncidence(prmIncidence);

        if (savedIncidence != null) {
            return new ResponseEntity<>(savedIncidence, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Obtener incidencia por ID", description = "Obtiene una incidencia por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Incidencia encontrada exitosamente", content = @Content(schema = @Schema(implementation = IncidenceRes.class))),
            @ApiResponse(responseCode = "404", description = "Incidencia no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<IncidenceRes>> getIncidenceById(@PathVariable("id") Integer incidenceID) {
        Optional<IncidenceRes> incidence = this.incidenceService.getIncidenceById(incidenceID);

        if (incidence.isPresent()) {
            return new ResponseEntity<>(incidence, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar incidencia por ID", description = "Elimina una incidencia por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Incidencia eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Incidencia no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIncidenceById(@PathVariable("id") Integer incidenceID) {
        boolean confirmation = this.incidenceService.deleteIncidenceById(incidenceID);

        if (confirmation) {
            return new ResponseEntity<>("Se ha eliminado la incidencia con id = " + incidenceID, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se eliminó la incidencia con id = " + incidenceID, HttpStatus.NOT_FOUND);
        }
    }

}
