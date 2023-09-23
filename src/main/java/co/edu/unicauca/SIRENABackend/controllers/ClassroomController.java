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

    /**
     * Crea una nueva instancia de aula (classroom) y la guarda en la base de datos.
     *
     * @param classroom El objeto ClassroomModel que se desea crear y guardar.
     * @return La instancia de ClassroomModel creada y almacenada en la base de datos.
     */
    @PostMapping
    public ClassroomModel save(@RequestBody ClassroomModel classroom) {
        return classRoomService.save(classroom);
    }

    /**
     * Obtiene una lista de todas las aulas registradas.
     *
     * @return Una lista de objetos ClassroomModel que representan todas las aulas.
     */
    @GetMapping
    public List<ClassroomModel> classrooms() {
        return classRoomService.findAll();
    }

    /**
     * Obtiene una aula por su identificador único.
     *
     * @param id El identificador único del aula que se desea obtener.
     * @return El objeto ClassroomModel encontrado, o null si no se encuentra.
     */
    @GetMapping("/{id}")
    public ClassroomModel show(@PathVariable Integer id) {
        return classRoomService.findById(id);
    }

    /**
     * Actualiza una aula existente por su identificador único.
     *
     * @param classroom El objeto ClassroomModel con los datos actualizados.
     * @param id        El identificador único del aula que se desea actualizar.
     * @return La instancia de ClassroomModel actualizada y almacenada en la base de datos.
     */
    @PutMapping("/{id}")
    public ClassroomModel update(@RequestBody ClassroomModel classroom, @PathVariable Integer id) {
        ClassroomModel classroomCurrent = classRoomService.findById(id);
        classroomCurrent.setName(classroom.getName());
        classroomCurrent.setBuilding(classroom.getBuilding());
        classroomCurrent.setCapacity(classroom.getCapacity());
        classroomCurrent.setState(classroom.getState());
        classroomCurrent.setClassroomType(classroom.getClassroomType());
        return classRoomService.save(classroomCurrent);
    }

    /**
     * Elimina un aula por su identificador único.
     *
     * @param id El identificador único del aula que se desea eliminar.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        classRoomService.delete(id);
    }
}