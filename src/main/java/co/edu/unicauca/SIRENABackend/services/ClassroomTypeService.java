package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.ClassroomTypeModel;
import co.edu.unicauca.SIRENABackend.repositories.IClassroomTypeRepository;

public class ClassroomTypeService {
    @Autowired
    private IClassroomTypeRepository classroomTypeRepository;

    @Transactional(readOnly = true)
    public ArrayList<ClassroomTypeModel> getUsers() {
        return (ArrayList<ClassroomTypeModel>) classroomTypeRepository.findAll();
    }

    @Transactional
    public ClassroomTypeModel saveUser(ClassroomTypeModel ClassroomTypeModel) {
        return classroomTypeRepository.save(ClassroomTypeModel);
    }


    @Transactional(readOnly = true)
    public Optional<ClassroomTypeModel> getClassroomTypeById(Integer prmId) {
        return classroomTypeRepository.findById(prmId);
    }

    @Transactional
    public boolean deleteClassroomTypeById(Integer prmId) {
        try {
            classroomTypeRepository.deleteById(prmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
