package co.edu.unicauca.SIRENABackend.services;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;

import java.util.List;

/**
 * Interfaz que define un servicio para operaciones relacionadas con aulas (classrooms).
 */
public interface ClassRoomService {

    /**
     * Guarda una instancia de ClassroomModel en la base de datos.
     *
     * @param classroom El objeto ClassroomModel que se desea guardar.
     * @return La instancia de ClassroomModel guardada en la base de datos.
     */
    ClassroomModel save(ClassroomModel classroom);

    /**
     * Elimina una aula por su identificador único.
     *
     * @param id El identificador único del aula que se desea eliminar.
     */
    void delete(Integer id);

    /**
     * Busca una aula por su identificador único.
     *
     * @param id El identificador único del aula que se desea buscar.
     * @return El objeto ClassroomModel encontrado, o null si no se encuentra.
     */
    ClassroomModel findById(Integer id);

    /**
     * Obtiene una lista de todas las aulas en la base de datos.
     *
     * @return Una lista de objetos ClassroomModel que representan todas las aulas.
     */
    List<ClassroomModel> findAll();
}
