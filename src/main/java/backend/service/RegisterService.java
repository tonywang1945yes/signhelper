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
        if (checkDuplicatedRegister(student.getEmail())) {
            throw new RegisterException("该邮箱已被注册过");
        } else if (checkDuplicatedVisaNum(student.getVisaNum())) {
            throw new RegisterException("该通行证号码已被使用过");
        }
//        addNewStudent(student);
        addNewStudent(student);
    }

    private void addNewStudent(Student s) throws RegisterException {
        HibernateDao<Student> studentDao = new HibernateDao<>(new Student());
        DatabaseRM res = studentDao.add(s);
        if (res != DatabaseRM.SUCCESS) {
            throw new RegisterException("学生注册失败");
        }
//        studentDao = null;
//        HibernateDao<ApplForm> applFormDao = new HibernateDao<>(new ApplForm());
//        ApplForm applForm = new ApplForm();
//        applForm.updateInfo(s);
//        res = applFormDao.add(applForm);
//        if (res != DatabaseRM.SUCCESS)
//            throw new RegisterException("申请表初始化失败");
//        String sql = "SELECT a from ApplForm a  WHERE a.studentId = \'"+applForm.getStudentId()+"\'";
//        applForm = applFormDao.executeQuerySql(sql).get(0);
//        applFormDao = null;
//        s.setApplFormId(applForm.getId());
//        studentDao = new HibernateDao<>(new Student());
//        res = studentDao.update(s);
//        if(res != DatabaseRM.SUCCESS)
//            throw new RegisterException("学生与申请表绑定失败");
        //迷之报错，先不用
    }

//    public void initApplForm(Student student) throws RegisterException {
//        HibernateDao<ApplForm> applFormDao = new HibernateDao<>(new ApplForm());
//        ApplForm applForm = new ApplForm();
//        applForm.updateInfo(student);
//        if (applFormDao.add(applForm) != DatabaseRM.SUCCESS)
//            throw new RegisterException("申请表初始化失败");
//    }
}
