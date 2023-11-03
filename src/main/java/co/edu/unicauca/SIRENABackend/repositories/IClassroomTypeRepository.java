package co.edu.unicauca.SIRENABackend.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.common.enums.ClassroomTypeEnum;
import co.edu.unicauca.SIRENABackend.models.ClassroomTypeModel;

public interface IClassroomTypeRepository extends JpaRepository<ClassroomTypeModel, Integer> {

    ArrayList<ClassroomTypeModel> findAll();

    boolean existsByName(ClassroomTypeEnum name);

    Optional<ClassroomTypeModel> findById(Integer id);
}
