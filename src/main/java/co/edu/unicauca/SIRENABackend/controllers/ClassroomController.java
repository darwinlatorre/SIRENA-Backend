package co.edu.unicauca.SIRENABackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import co.edu.unicauca.SIRENABackend.services.ClassRoomService;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/v1/classroom")
public class ClassroomController {
    @Autowired
    private ClassRoomService classRoomService;
    @PostMapping
    public ClassroomModel save(@RequestBody ClassroomModel classroom){
        return  classRoomService.save(classroom);
    }

    @GetMapping
    public List<ClassroomModel> classrooms(){
        return  classRoomService.findAll();
    }

    @GetMapping("/{id}")
    public ClassroomModel show(@PathVariable Integer id){
        return  classRoomService.findById(id);
    }
    @PutMapping("/{id}")
    public ClassroomModel update(@RequestBody ClassroomModel classroom, @PathVariable Integer id){
        ClassroomModel classroomCurrent=classRoomService.findById(id);
        classroomCurrent.setClsName(classroom.getClsName());
        classroomCurrent.setClsBuilding(classroom.getClsBuilding());
        classroomCurrent.setClsCapacity(classroom.getClsCapacity());
        classroomCurrent.setClsState(classroom.getClsState());
        classroomCurrent.setClsType(classroom.getClsType());
        return  classRoomService.save(classroomCurrent);
    }
    @DeleteMapping("/{id}")
    public  void delete(@PathVariable Integer id){
        classRoomService.delete(id);
    }
}
