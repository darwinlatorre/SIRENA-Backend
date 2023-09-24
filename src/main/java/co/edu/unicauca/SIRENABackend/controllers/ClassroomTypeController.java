package co.edu.unicauca.SIRENABackend.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.ClassroomTypeModel;
import co.edu.unicauca.SIRENABackend.services.ClassroomTypeService;

@RestController
@RequestMapping("/api/v1/classroomType")
public class ClassroomTypeController {
    @Autowired
    ClassroomTypeService classroomTypeService;

    @GetMapping()
    public ArrayList<ClassroomTypeModel> getClassroomType() {
        return classroomTypeService.getClassroomType();
    }

    @PostMapping()
    public ClassroomTypeModel saveClassroomType(@RequestBody ClassroomTypeModel prmClassroom) {
        return this.classroomTypeService.saveClassroomType(prmClassroom);
    }


    @GetMapping(path = "/{id}")
    public Optional<ClassroomTypeModel> getClassroomTypeById(@PathVariable("id") Integer prmClassroomID) {
        return this.classroomTypeService.getClassroomTypeById(prmClassroomID);
    }


    @DeleteMapping(path = "/{id}")
    public String deleteClassroomTypeById(@PathVariable("id") Integer prmClassroomID) {
        boolean confirmation = this.classroomTypeService.deleteClassroomTypeById(prmClassroomID);
        if (confirmation) {
            return "Se ha eliminado el incidencia con id = " + prmClassroomID;
        } else {
            return "No se eliminó el incidencia con id = " + prmClassroomID;
        }
    }
}
