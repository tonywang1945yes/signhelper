package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class LogoutService {

    @Autowired
    StudentRepository repository;

    public void updateLastLogoutDate(String email) {
        Student s = repository.getOne(email);
        s.setLastLogOutDate(Calendar.getInstance());
        repository.save(s);
    }
}
