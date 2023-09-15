package co.edu.unicauca.SIRENABackend.services;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;

import java.util.List;

public interface ClassRoomService {
    public ClassroomModel save(ClassroomModel classroom);
    public  void delete(Integer id);
    public ClassroomModel findById(Integer id);
    public List<ClassroomModel> findAll();

}
