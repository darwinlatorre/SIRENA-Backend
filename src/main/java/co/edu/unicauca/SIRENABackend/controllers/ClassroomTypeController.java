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

import co.edu.unicauca.SIRENABackend.dtos.response.ClassroomTypeRes;
import co.edu.unicauca.SIRENABackend.services.ClassroomTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/classroomType")
public class ClassroomTypeController {
    @Autowired
    ClassroomTypeService classroomTypeService;

    @Operation(summary = "Obtener tipos de aulas", description = "Obtiene una lista de tipos de aulas.", responses = {
            @ApiResponse(responseCode = "200", description = "Tipos de aulas encontrados exitosamente", content = @Content(schema = @Schema(implementation = ClassroomTypeRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontraron tipos de aulas", content = @Content(mediaType = "application/json"))
    })
    @GetMapping()
    public ResponseEntity<ArrayList<ClassroomTypeRes>> getClassroomType() {
        ArrayList<ClassroomTypeRes> classroomTypes = this.classroomTypeService.getClassroomType();

        if (!classroomTypes.isEmpty()) {
            return new ResponseEntity<>(classroomTypes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener tipo de aula por ID", description = "Obtiene un tipo de aula por su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Tipo de aula encontrado exitosamente", content = @Content(schema = @Schema(implementation = ClassroomTypeRes.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Tipo de aula no encontrado", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ClassroomTypeRes>> getClassroomTypeById(
            @PathVariable("id") Integer prmClassroomID) {
        Optional<ClassroomTypeRes> classroomType = this.classroomTypeService.getClassroomTypeById(prmClassroomID);

        if (classroomType.isPresent()) {
            return new ResponseEntity<>(classroomType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
