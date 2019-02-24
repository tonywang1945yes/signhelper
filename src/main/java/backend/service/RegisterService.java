package backend.service;

import backend.dao.service.ApplFormRepository;
import backend.dao.service.StudentRepository;
import backend.dao.service.UserRepository;
import backend.entity.Student;
import backend.entity.User;
import backend.entity.application.ApplForm;
import backend.exception.RegisterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {
    @Autowired
    ApplFormRepository applFormRepo;

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    UserRepository userRepo;

    public boolean checkDuplicatedRegister(String id) {
        return studentRepo.existsById(id);
    }

    public boolean checkDuplicatedVisaNum(String visaNum) {
        List<Student> result = studentRepo.findByVisaNum(visaNum);
        return (result != null && result.size() != 0);
    }

    public void register(User user, Student student) throws RegisterException {
        if (checkDuplicatedRegister(student.getEmail())) {
            throw new RegisterException("该邮箱已被注册过");
        } else if (checkDuplicatedVisaNum(student.getVisaNum())) {
            throw new RegisterException("该通行证号码已被使用过");
        }
        userRepo.save(user);
        bindStudentAndApplForm(student);
    }

    private void bindStudentAndApplForm(Student s) throws RegisterException {
        try {
            Student res = studentRepo.save(s);
            ApplForm applForm = new ApplForm();
            applForm.updateInfo(s);
            ApplForm a = applFormRepo.save(applForm);
            res.setApplFormId(a.getId());
            studentRepo.save(res);
        }catch (Exception e){
            e.printStackTrace();
            throw new RegisterException("注册失败");
        }

    }

}
