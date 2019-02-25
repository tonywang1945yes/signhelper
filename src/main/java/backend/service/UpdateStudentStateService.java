package backend.service;

import backend.dao.service.AdministerRepository;
import backend.dao.service.AssessmentResultRepository;
import backend.dao.service.StudentRepository;
import backend.entity.AssessmentResult;
import backend.entity.Student;
import backend.enums.AdministerState;
import backend.enums.StudentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateStudentStateService {

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    AssessmentResultRepository assessmentResultRepo;

    @Autowired
    AdministerRepository administerRepo;

    public Boolean updateState(String id, StudentState state) {
        Student student = studentRepo.findByEmail(id).get(0);
        student.setStudentState(state);
        studentRepo.save(student);

        AdministerState administerState = administerRepo.findAll().get(0).getState();
        AssessmentResult result;

        if (administerState == AdministerState.JUNIOR_CHECKING){
            result  = new AssessmentResult();
            result.setEmail(id);
            result.setFirstAssessment(state == StudentState.JUNIOR_PASSED);
        }else{
            result = assessmentResultRepo.findByEmail(id);
            result.setSecondAssessment(state == StudentState.SENIOR_PASSED);
        }
        assessmentResultRepo.save(result);


        Student new_student = studentRepo.findByEmail(id).get(0);
        return new_student.getStudentState() == state;
    }
}
