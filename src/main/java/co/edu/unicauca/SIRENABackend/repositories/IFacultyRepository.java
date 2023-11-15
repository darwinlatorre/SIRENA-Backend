package co.edu.unicauca.SIRENABackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.SIRENABackend.models.FacultyModel;

public interface IFacultyRepository extends JpaRepository<FacultyModel, Integer> {
}
