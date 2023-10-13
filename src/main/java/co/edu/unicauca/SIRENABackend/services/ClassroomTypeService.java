package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.ClassroomTypeModel;
import co.edu.unicauca.SIRENABackend.repositories.IClassroomTypeRepository;

@Service
public class ClassroomTypeService {
    @Autowired
    private IClassroomTypeRepository classroomTypeRepository;

    @Transactional(readOnly = true)
    public ArrayList<ClassroomTypeModel> getClassroomType() {
        return (ArrayList<ClassroomTypeModel>) classroomTypeRepository.findAll();
    }

    @Transactional
    public ClassroomTypeModel saveClassroomType(ClassroomTypeModel classroomTypeModel) {
        return classroomTypeRepository.save(classroomTypeModel);
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