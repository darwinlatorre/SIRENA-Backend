package co.edu.unicauca.SIRENABackend.dao;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import org.springframework.data.repository.CrudRepository;

public interface ClassroomDao extends CrudRepository<ClassroomModel,Integer> {
}
