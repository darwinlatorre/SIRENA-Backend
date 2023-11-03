package co.edu.unicauca.SIRENABackend.services.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.dtos.request.ClassroomTypeReq;
import co.edu.unicauca.SIRENABackend.dtos.response.ClassroomTypeRes;
import co.edu.unicauca.SIRENABackend.models.ClassroomTypeModel;
import co.edu.unicauca.SIRENABackend.repositories.IClassroomTypeRepository;
import co.edu.unicauca.SIRENABackend.services.ClassroomTypeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassroomTypeServiceImpl implements ClassroomTypeService {

    private final IClassroomTypeRepository classroomTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public ArrayList<ClassroomTypeRes> getClassroomType() {
        ArrayList<ClassroomTypeModel> classroomTypes = classroomTypeRepository.findAll();
        ArrayList<ClassroomTypeRes> classroomTypesRes = new ArrayList<>();

        for (ClassroomTypeModel classroomType : classroomTypes) {
            ClassroomTypeRes classroomTypeTemp = ClassroomTypeRes.builder()
                    .id(classroomType.getId())
                    .name(classroomType.getName())
                    .build();
            classroomTypesRes.add(classroomTypeTemp);
        }

        return classroomTypesRes;
    }

    @Override
    @Transactional
    public ClassroomTypeRes saveClassroomType(ClassroomTypeReq classroomTypeModel) {
        if (classroomTypeRepository.existsByName(classroomTypeModel.getName())) {
            return null;
        }

        ClassroomTypeModel classroomType = ClassroomTypeModel.builder()
                .name(classroomTypeModel.getName())
                .build();

        ClassroomTypeModel classroomTypeSave = classroomTypeRepository.save(classroomType);

        ClassroomTypeRes classroomTypeRes = ClassroomTypeRes.builder()
                .id(classroomTypeSave.getId())
                .name(classroomTypeSave.getName())
                .build();

        return classroomTypeRes;
    }

    @Transactional(readOnly = true)
    public Optional<ClassroomTypeRes> getClassroomTypeById(Integer prmId) {
        Optional<ClassroomTypeModel> classroomFound = classroomTypeRepository.findById(prmId);

        ClassroomTypeRes classroomTypeRes = classroomFound.map(classroomType -> ClassroomTypeRes.builder()
                .id(classroomType.getId())
                .name(classroomType.getName())
                .build()).orElse(null);

        return classroomTypeRes != null ? Optional.of(classroomTypeRes) : Optional.empty();
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