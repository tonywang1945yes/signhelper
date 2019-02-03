package backend.service;

import backend.dao.service.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class LogoutService {
    @Autowired
    StudentRepository repository;

    public void updateLastLogoutDate(String email){
        repository.updateLastLogoutDate(Calendar.getInstance(), email);
    }
}
