package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import backend.entity.application.ApplForm;
import backend.enums.resultMessage.DatabaseRM;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    public boolean addApplicationForm(ApplForm applForm) {
        HibernateDao<ApplForm> applFormRepo = new HibernateDao<>(new ApplForm());
        HibernateDao<Student> studentRepo = new HibernateDao<>(new Student());
        DatabaseRM res = applFormRepo.add(applForm);
        if (res == DatabaseRM.SUCCESS) {
            String sql = "SELECT * FROM tbl_application_form WHERE student_id = " + applForm.getStudentId();
            Long applFormId = applFormRepo.executeQuerySql(sql).get(0).getId();
            Student s = studentRepo.findByKey(applForm.getStudentId());
            s.setApplFormId(applFormId);
            res = studentRepo.update(s);
            return res == DatabaseRM.SUCCESS;
        }
        return false;
    }

}
