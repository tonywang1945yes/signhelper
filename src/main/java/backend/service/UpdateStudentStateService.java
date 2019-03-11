package backend.service;

import backend.dao.service.AdministerRepository;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
import backend.enums.AdministerState;
import backend.enums.StudentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static backend.enums.StudentState.*;
import static backend.enums.StudentState.JUNIOR_PASSED;

@Service
public class UpdateStudentStateService {

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    AdministerRepository administerRepo;

    public Boolean updateState(String id, int from) {
        StudentState state = StudentState.JUNIOR_PASSED;
        switch (from){
            case 0:state = JUNIOR_PASSED;break;
            case 1:state = JUNIOR_FAILED;break;
            case 2:state = NULL;break;
            case 3:state = SENIOR_PASSED;break;
            case 4:state = SENIOR_FAILED;break;
            case 5:state = JUNIOR_PASSED;break;
        }
        Student student = studentRepo.findByEmail(id).get(0);
        student.setStudentState(state);
        studentRepo.save(student);

        Student new_student = studentRepo.findByEmail(id).get(0);
        return new_student.getStudentState() == state;
    }
}
