package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class LogoutService {

    public void updateLastLogoutDate(String email) {
        HibernateDao<Student> dao = new HibernateDao<>(new Student());
        Student s = dao.findByKey(email);
        s.setLastLogOutDate(Calendar.getInstance());
        dao.update(s);
    }
}
