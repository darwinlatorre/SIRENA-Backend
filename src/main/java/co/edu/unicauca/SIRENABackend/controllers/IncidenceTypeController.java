package co.edu.unicauca.SIRENABackend.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.dtos.response.IncidenceTypeRes;
import co.edu.unicauca.SIRENABackend.services.IncidenceTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/incidenceType")
public class IncidenceTypeController {
    @Autowired
    IncidenceTypeService incidenceTypeService;

    @Operation(summary = "Obtener tipos de incidencia", description = "Obtiene una lista de tipos de incidencia.", responses = {
            @ApiResponse(responseCode = "200", description = "Tipos de incidencia encontrados exitosamente", content = @Content(schema = @Schema(implementation = IncidenceTypeRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontraron tipos de incidencia", content = @Content(mediaType = "application/json"))
    })
    @GetMapping()
    public ResponseEntity<ArrayList<IncidenceTypeRes>> getIncidenceTypes() {
        ArrayList<IncidenceTypeRes> incidenceTypes = incidenceTypeService.getIncidenceTypes();

        if (!incidenceTypes.isEmpty()) {
            return new ResponseEntity<>(incidenceTypes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener tipo de incidencia por ID", description = "Obtiene un tipo de incidencia por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Tipo de incidencia encontrado exitosamente", content = @Content(schema = @Schema(implementation = IncidenceTypeRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Tipo de incidencia no encontrado", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<IncidenceTypeRes>> getIncidenceTypeById(
            @PathVariable("id") Integer incidenceTypeID) {
        Optional<IncidenceTypeRes> incidenceType = this.incidenceTypeService.getIncidenceTypeById(incidenceTypeID);

        if (incidenceType.isPresent()) {
            return new ResponseEntity<>(incidenceType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
