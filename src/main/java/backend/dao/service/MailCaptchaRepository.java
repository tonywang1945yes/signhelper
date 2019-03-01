package backend.dao.service;


import backend.entity.MailCaptcha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailCaptchaRepository extends JpaRepository<MailCaptcha,Long> {

    List<MailCaptcha> findByEmailAddress(String emailAddress);

    int deleteByEmailAddress(String emailAddress);
}
