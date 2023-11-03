package co.edu.unicauca.SIRENABackend.services;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.unicauca.SIRENABackend.dtos.request.ClassroomTypeReq;
import co.edu.unicauca.SIRENABackend.dtos.response.ClassroomTypeRes;

public interface ClassroomTypeService {

    ArrayList<ClassroomTypeRes> getClassroomType();

    ClassroomTypeRes saveClassroomType(ClassroomTypeReq classroomTypeReq);

    Optional<ClassroomTypeRes> getClassroomTypeById(Integer prmId);

    boolean deleteClassroomTypeById(Integer prmId);
}