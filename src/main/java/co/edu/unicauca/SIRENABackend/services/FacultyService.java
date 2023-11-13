package co.edu.unicauca.SIRENABackend.services;

import co.edu.unicauca.SIRENABackend.models.BuildingModel;
import co.edu.unicauca.SIRENABackend.models.FacultyModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface FacultyService {
    List<FacultyModel> getFaculties();

    FacultyModel saveFaculties(FacultyModel prmIncidence);

    Optional<FacultyModel> getFacultyById(Integer prmId);

    boolean deleteFacultyById(Integer prmId);
}
