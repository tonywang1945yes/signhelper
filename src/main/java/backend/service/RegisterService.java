package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    public boolean checkDumplicatedRegister(String id){
        HibernateDao<Student> dao = new HibernateDao<>(new Student());
        Student user = dao.findByKey(id);
        if (user == null){
            return true;
        }
        else {
            return false;
        }
    }
}
