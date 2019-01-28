package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import org.springframework.stereotype.Service;
import backend.util.secureUtil.PasswordHash;

@Service
public class LoginService {
    public  boolean checkPassword(String sname,String pwd){
        HibernateDao<Student> dao=new HibernateDao<Student>(new Student());
        Student student =dao.findByKey(sname);
        try {
            if (student != null && PasswordHash.validatePassword(pwd, student.getPasswordHash())) {
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
