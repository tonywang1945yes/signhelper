package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backend.util.secureUtil.PasswordHash;

@Service
public class LoginService {
    @Autowired
    StudentRepository studentRepository;

    public boolean checkPassword(String emailAddress, String pwd) {
//        HibernateDao<Student> dao = new HibernateDao<Student>(new Student());
//        Student student = dao.findByKey(emailAddress);
        Student student=studentRepository.getOne(emailAddress);
        try {
            if (student != null && PasswordHash.validatePassword(pwd, student.getPasswordHash())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
