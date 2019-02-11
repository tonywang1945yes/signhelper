package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import backend.entity.application.ApplForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {
    public boolean checkDuplicatedRegister(String id) {
        HibernateDao<Student> studentRepo = new HibernateDao<>(new Student());
        HibernateDao<ApplForm> applFormRepo = new HibernateDao<>(new ApplForm());
        Student user = studentRepo.findByKey(id);
        return user != null;
    }

    public boolean checkDuplicatedVisaNum(String visaNum) {
        String sql = "SELECT * FROM tbl_student WHERE visa_num = " + visaNum;
        HibernateDao<Student> dao = new HibernateDao<>(new Student());
        List<Student> result = dao.executeQuerySql(sql);
        return (result != null && result.size() != 0);
    }
}
