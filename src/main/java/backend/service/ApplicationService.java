package backend.service;

import backend.dao.service.*;
import backend.entity.Student;
import backend.entity.application.Activity;
import backend.entity.application.ApplForm;
import backend.entity.application.FamilyParticularItem;
import backend.parameter.application.ApplFormParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    ApplFormRepository applFormRepo;

    @Autowired
    ActivityRepository activityRepo;

    @Autowired
    FamilyParticularRepository familyParticularRepo;

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    AdministerRepository administerRepo;


    public boolean updateApplForm(ApplForm applForm) {
//        if (applForm == null)
//            return false;
//        ApplForm a = applFormRepo.save(applForm);
//        if (activities != null) {
//            for (Activity activity : activities){
//                activity.setFormId(a.getIdentityNum());
//            }
//            activityRepo.saveAll(activities);
//        }
//        return true;
//        if (activities != null)
//            applForm.setActivities(new ArrayList<>(Arrays.asList(activities)));
        applFormRepo.save(applForm);
        return true;
    }

    public boolean updateApplForm(ApplFormParameter param, String email) {
        ApplForm applForm = applFormRepo.findByStudentId(email).get(0);
//        if (applForm.getActivities() != null)
//            activityRepo.deleteAll(applForm.getActivities());
//        if (applForm.getFamilyParticulars() != null)
//            familyParticularRepo.deleteAll(applForm.getFamilyParticulars());
        applForm.updateInfo(param);
        applFormRepo.save(applForm);
        return true;
    }

    public ApplForm getApplicationForm(String studentId) {
        return applFormRepo.findByStudentId(studentId).get(0);
    }

    public String getStudentName(String email) {
        return studentRepo.getOne(email).getName();
    }

    public boolean beforeDDL() {
        Calendar now = Calendar.getInstance();
        Calendar ddl = administerRepo.findAll().get(0).getDdl();
        return now.before(ddl);
    }

}
