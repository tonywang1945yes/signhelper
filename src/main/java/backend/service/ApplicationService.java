package backend.service;

import backend.dao.service.*;
import backend.entity.application.ApplForm;
import backend.parameter.application.ApplFormParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

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

    @Transactional
    public boolean updateApplForm(ApplFormParameter param, String email) {
        ApplForm applForm = applFormRepo.findByStudentId(email).get(0);
        if (applForm.getActivities() != null) {
            activityRepo.deleteActivities(applForm.getId());
        }
        if (applForm.getFamilyParticulars() != null) {
            familyParticularRepo.deleteItems(applForm.getId());
        }
        ApplForm updated = applFormRepo.saveAndFlush(applForm);
        updated.updateInfo(param);
        applFormRepo.saveAndFlush(updated);
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
