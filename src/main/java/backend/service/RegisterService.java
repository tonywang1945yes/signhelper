package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.ApplFormRepository;
import backend.entity.Student;
import backend.entity.application.ApplForm;
import backend.enums.resultMessage.DatabaseRM;
import backend.exception.RegisterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {
    @Autowired
    ApplFormRepository repository;

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
        if (checkDuplicatedRegister(student.getEmail())) {
            throw new RegisterException("该邮箱已被注册过");
        } else if (checkDuplicatedVisaNum(student.getVisaNum())) {
            throw new RegisterException("该通行证号码已被使用过");
        }
        bindStudentAndApplForm(student);
    }

    //两种数据库技术混着用过于沙雕，会不会有一天炸了啊
    private void bindStudentAndApplForm(Student s) throws RegisterException {
        HibernateDao<Student> studentDao = new HibernateDao<>(new Student());
        DatabaseRM res = studentDao.add(s);
        if (res != DatabaseRM.SUCCESS) {
            throw new RegisterException("学生注册失败");
        }

        ApplForm applForm = new ApplForm();
        applForm.updateInfo(s);
        ApplForm a = repository.save(applForm);

        s.setApplFormId(a.getId());
        res = studentDao.update(s);
        if (res != DatabaseRM.SUCCESS) {
            throw new RegisterException("绑定学生和注册表失败");
        }

    }

}
