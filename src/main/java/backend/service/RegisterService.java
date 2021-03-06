package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import backend.enums.resultMessage.DatabaseRM;
import backend.exception.RegisterException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {
    public boolean checkDuplicatedRegister(String id) {
        HibernateDao<Student> dao = new HibernateDao<>(new Student());
        return dao.checkKeyExists(id);
    }

    public boolean checkDuplicatedVisaNum(String visaNum) {
        HibernateDao<Student> dao = new HibernateDao<>(new Student());
        String sql = "SELECT s FROM Student s WHERE s.visaNum = \'" + visaNum + "\'";
        List<Student> result = dao.executeQuerySql(sql);
        return (result != null && result.size() != 0);
    }

    public void register(Student student) throws RegisterException {
        HibernateDao<Student> dao = new HibernateDao<>(new Student());
        if (checkDuplicatedRegister(student.getEmail())) {
            throw new RegisterException("该邮箱已被注册过");
        } else if (checkDuplicatedVisaNum(student.getVisaNum())) {
            throw new RegisterException("通行证号码已被使用过");
        }
        DatabaseRM res = dao.add(student);
        if (res != DatabaseRM.SUCCESS)
            throw new RegisterException("注册失败");
    }
}
