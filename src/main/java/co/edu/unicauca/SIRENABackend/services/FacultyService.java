package co.edu.unicauca.SIRENABackend.services;

import java.util.List;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.models.FacultyModel;

public interface FacultyService {
    List<FacultyModel> getFaculties();

    FacultyModel saveFaculties(FacultyModel prmIncidence);

    Optional<FacultyModel> getFacultyById(Integer prmId);

    boolean deleteFacultyById(Integer prmId);
}
