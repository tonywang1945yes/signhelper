package backend.service;

import backend.dao.service.StudentRepository;
import backend.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backend.util.secureUtil.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class LoginService {
    @Autowired
    StudentRepository studentRepository;

    public boolean checkPassword(String emailAddress, String pwd) throws InvalidKeySpecException, NoSuchAlgorithmException {
//        HibernateDao<Student> dao = new HibernateDao<Student>(new Student());
//        Student student = dao.findByKey(emailAddress);
        Student student = studentRepository.getOne(emailAddress);
        if (student != null && PasswordHash.validatePassword(pwd, student.getPassword())) {
            return true;
        }
        return false;
    }
}
