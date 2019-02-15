package backend.service;

import backend.dao.service.ActivityRepository;
import backend.dao.service.ApplFormRepository;
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

//    public boolean addApplicationForm(ApplForm applForm) {
//        HibernateDao<ApplForm> applFormRepo = new HibernateDao<>(new ApplForm());
//        HibernateDao<Student> studentRepo = new HibernateDao<>(new Student());
//        DatabaseRM res = applFormRepo.add(applForm);
//        if (res == DatabaseRM.SUCCESS) {
//            String sql = "SELECT * FROM tbl_application_form WHERE student_id = " + applForm.getStudentId();
//            Long applFormId = applFormRepo.executeQuerySql(sql).get(0).getId();
//            Student s = studentRepo.findByKey(applForm.getStudentId());
//            s.setApplFormId(applFormId);
//            res = studentRepo.update(s);
//            return res == DatabaseRM.SUCCESS;
//        }
//        return false;
//    }

    public boolean updateApplFormAndActivities(ApplForm applForm, List<Activity> activities) {
        if (applForm == null)
            return false;
        ApplForm a = applFormRepo.save(applForm);
        if (activities != null) {
            for (Activity activity : activities){
                activity.setFormId(a.getId());
            }
            activityRepo.saveAll(activities);
        }
        return true;
    }


    public ApplForm getApplicationForm(String studentId) {
        return applFormRepo.findByStudentId(studentId);
    }


}
