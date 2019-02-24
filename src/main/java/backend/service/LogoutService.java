package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.StudentRepository;
import backend.dao.service.UserRepository;
import backend.entity.Student;
import backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class LogoutService {

    @Autowired
    UserRepository repository;

    public void updateLastLogoutDate(String email) {
        User u  = repository.getOne(email);
        u.setLastLogoutDate(Calendar.getInstance());
        repository.save(u);
    }
}
