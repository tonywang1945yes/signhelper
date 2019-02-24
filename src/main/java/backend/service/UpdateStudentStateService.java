package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
import backend.enums.StudentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateStudentStateService {

    @Autowired
    StudentRepository studentRepo;

    public Boolean updateState(String id, StudentState state){
        Student student = studentRepo.findByEmail(id).get(0);
        student.setStudentState(state);
        studentRepo.save(student);

        Student new_student = studentRepo.findByEmail(id).get(0);
        return new_student.getStudentState() == state;
    }
}
