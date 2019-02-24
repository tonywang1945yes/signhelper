package backend.dao.service;


import backend.entity.MailCaptcha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailCaptchaRepository extends JpaRepository<MailCaptcha,Long> {
}
