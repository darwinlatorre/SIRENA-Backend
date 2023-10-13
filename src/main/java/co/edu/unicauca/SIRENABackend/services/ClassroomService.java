package co.edu.unicauca.SIRENABackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import co.edu.unicauca.SIRENABackend.repositories.IClassroomRepository;

@Service
public class ClassroomService {

    @Autowired
    private IClassroomRepository classroomDao;

    @Transactional(readOnly = false)
    public ClassroomModel save(ClassroomModel classroom) {
        return classroomDao.save(classroom);
    }

    @Transactional(readOnly = false)
    public void delete(Integer id) {
        classroomDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<ClassroomModel> findById(Integer id) {
        return classroomDao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ClassroomModel> findAll() {
        return (List<ClassroomModel>) classroomDao.findAll();
    }
}
