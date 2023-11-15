package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.FacultyModel;
import co.edu.unicauca.SIRENABackend.repositories.IFacultyRepository;
import co.edu.unicauca.SIRENABackend.services.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private IFacultyRepository facultyReposirtory;

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
