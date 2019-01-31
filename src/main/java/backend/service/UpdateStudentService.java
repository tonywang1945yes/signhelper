package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import backend.enums.StudentState;
import org.springframework.stereotype.Service;

@Service
public class UpdateStudentService {
    public boolean updateState(String id, StudentState state){
        HibernateDao<Student> dao = new HibernateDao<>(new Student());
        Student student = dao.findByKey(id);
        student.setStudentState(state);
        dao.update(student);

        Student new_student = dao.findByKey(id);
        return new_student.getStudentState() == state;
    }
}
