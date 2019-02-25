package backend.service;

import backend.dao.service.ActivityRepository;
import backend.dao.service.ApplFormRepository;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
import backend.entity.application.Activity;
import backend.entity.application.ApplForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    ApplFormRepository applFormRepo;

    @Autowired
    ActivityRepository activityRepo;

    @Autowired
    StudentRepository studentRepo;


    public boolean updateApplFormAndActivities(ApplForm applForm, List<Activity> activities) {
//        if (applForm == null)
//            return false;
//        ApplForm a = applFormRepo.save(applForm);
//        if (activities != null) {
//            for (Activity activity : activities){
//                activity.setFormId(a.getId());
//            }
//            activityRepo.saveAll(activities);
//        }
//        return true;
        applForm.setActivities(activities);
        applFormRepo.save(applForm);
        return true;
    }

    public ApplForm getApplicationForm(String studentId) {
        return applFormRepo.findByStudentId(studentId).get(0);
    }

    public Student getStudent(String email){
        return studentRepo.getOne(email);
    }


}
