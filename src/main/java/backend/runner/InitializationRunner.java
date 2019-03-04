package backend.runner;

import backend.dao.service.UserRepository;
import backend.entity.User;
import backend.enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class InitializationRunner implements ApplicationRunner {

    @Autowired
    UserRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!repository.existsById("123456789@admin.com")) {
            User user = new User();
            user.setName("123456789@admin.com");
            user.setPasswordEncoded("$2a$10$Oz4Lgtlym8tcN70zCkK2weOUv2cb5hqNW.S52pZvVI6wL91MC85w6");
            user.setRoleName(RoleName.ROLE_ADMIN);
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -2);
            user.setLastLogoutDate((Calendar) c.clone());
            user.setLastPasswordResetDate((Calendar) c.clone());
            repository.save(user);
        }
    }
}
