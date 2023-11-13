package co.edu.unicauca.SIRENABackend.services.impl;

import co.edu.unicauca.SIRENABackend.models.BuildingModel;
import co.edu.unicauca.SIRENABackend.models.FacultyModel;
import co.edu.unicauca.SIRENABackend.repositories.IBuildingRespository;
import co.edu.unicauca.SIRENABackend.repositories.IFacultyRepository;
import co.edu.unicauca.SIRENABackend.services.BuildingService;
import co.edu.unicauca.SIRENABackend.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private IFacultyRepository facultyReposirtory;
    @Autowired
    private IBuildingRespository buildingReposirtory;

    @Transactional(readOnly = false)
    public FacultyModel saveFaculties(FacultyModel faculty) {
        return facultyReposirtory.save(faculty);
    }

    @Transactional(readOnly = false)
    public boolean deleteFacultyById(Integer id) {
        try {
            facultyReposirtory.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public Optional<FacultyModel> getFacultyById(Integer id) {
        return facultyReposirtory.findById(id);
    }

    @Transactional(readOnly = true)
    public List<FacultyModel> getFaculties() {
        return (List<FacultyModel>) facultyReposirtory.findAll();
    }
}
