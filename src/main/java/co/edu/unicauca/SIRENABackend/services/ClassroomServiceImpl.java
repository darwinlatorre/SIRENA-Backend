package co.edu.unicauca.SIRENABackend.services;

import co.edu.unicauca.SIRENABackend.dao.ClassroomDao;
import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassroomDao classroomDao;

    @Override
    @Transactional(readOnly = false)
    public ClassroomModel save(ClassroomModel classroom) {
        return classroomDao.save(classroom);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        classroomDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ClassroomModel findById(Integer id) {
        return classroomDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClassroomModel> findAll() {
        return (List<ClassroomModel>) classroomDao.findAll();
    }

}
