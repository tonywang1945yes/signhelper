package backend.service;

import backend.dao.service.AdministerRepository;
import backend.dao.service.ApplFormRepository;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
import backend.entity.application.ApplForm;
import backend.enums.AdministerState;
import backend.enums.StudentState;
import backend.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static backend.enums.StudentState.*;
import static backend.enums.StudentState.JUNIOR_PASSED;

@Service
public class UpdateStudentStateService {

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    ApplFormRepository applyRepo;

    @Autowired
    AdministerRepository administerRepo;

    public BasicResponse updateState(String identity, int from) {
        BasicResponse response = new BasicResponse();

        StudentState state = StudentState.JUNIOR_PASSED;
        switch (from) {
            case 0:
                state = JUNIOR_PASSED;
                break;
            case 1:
                state = JUNIOR_FAILED;
                break;
            case 3:
                state = SENIOR_PASSED;
                break;
            case 4:
                state = SENIOR_FAILED;
                break;
            default:
                response.setMsg("No such stustate");
                response.setSucceed(false);
                return response;
        }
        ApplForm form = applyRepo.findByIdentityNum(identity);

        Student student = studentRepo.findById(form.getStudentId()).get();
        student.setStudentState(state);
        studentRepo.save(student);
        response.setSucceed(true);
        return response;
    }
}
