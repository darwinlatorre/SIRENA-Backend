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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import co.edu.unicauca.SIRENABackend.services.ClassroomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    @Autowired
    private ClassroomService ClassroomService;

    @Operation(summary = "Create a new classroom", description = "Creates a new classroom.", responses = {
            @ApiResponse(responseCode = "201", description = "Classroom created successfully", content = @Content(schema = @Schema(implementation = ClassroomModel.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping()
    public ResponseEntity<ClassroomModel> save(@RequestBody ClassroomModel classroom) {
        System.out.println(classroom);
        ClassroomModel savedClassroom = this.ClassroomService.save(classroom);
        return new ResponseEntity<>(savedClassroom, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a list of classrooms", description = "Retrieves a list of all classrooms.", responses = {
            @ApiResponse(responseCode = "200", description = "Classrooms retrieved successfully", content = @Content(schema = @Schema(implementation = ClassroomModel.class))),
            @ApiResponse(responseCode = "404", description = "Classrooms not found")
    })
    @GetMapping
    public ResponseEntity<List<ClassroomModel>> classrooms() {
        List<ClassroomModel> classroomList = this.ClassroomService.findAll();
        return new ResponseEntity<>(classroomList, HttpStatus.OK);
    }

    @Operation(summary = "Get a classroom by ID", description = "Retrieves a classroom by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Classroom retrieved successfully", content = @Content(schema = @Schema(implementation = ClassroomModel.class))),
            @ApiResponse(responseCode = "404", description = "Classroom not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClassroomModel> show(@PathVariable Integer id) {
        Optional<ClassroomModel> classroomOptional = this.ClassroomService.findById(id);

        if (classroomOptional.isPresent()) {
            ClassroomModel classroom = classroomOptional.get();
            return new ResponseEntity<>(classroom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update a classroom by ID", description = "Updates a classroom by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Classroom updated successfully", content = @Content(schema = @Schema(implementation = ClassroomModel.class))),
            @ApiResponse(responseCode = "404", description = "Classroom not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClassroomModel> update(@RequestBody ClassroomModel classroom, @PathVariable Integer id) {
        Optional<ClassroomModel> classroomCurrentOptional = this.ClassroomService.findById(id);

        if (classroomCurrentOptional.isPresent()) {
            ClassroomModel classroomCurrent = classroomCurrentOptional.get();
            classroomCurrent.setName(classroom.getName());
            classroomCurrent.setBuilding(classroom.getBuilding());
            classroomCurrent.setCapacity(classroom.getCapacity());
            classroomCurrent.setState(classroom.getState());
            classroomCurrent.setClassroomType(classroom.getClassroomType());

            ClassroomModel updatedClassroom = ClassroomService.save(classroomCurrent);
            return new ResponseEntity<>(updatedClassroom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a classroom by ID", description = "Deletes a classroom by its ID.", responses = {
            @ApiResponse(responseCode = "204", description = "Classroom deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Classroom not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<ClassroomModel> classroomOptional = this.ClassroomService.findById(id);

        if (classroomOptional.isPresent()) {
            ClassroomService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
