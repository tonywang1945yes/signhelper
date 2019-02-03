package backend.dao.service;

import backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Calendar;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findByEmail(String email);

    @Modifying
    @Query("update Student s set s.lastLogOutDate = ?1 where s.email = ?2" )
    void updateLastLogoutDate(Calendar now, String email);

    @Modifying
    @Query("update Student s set s.password=?1, s.lastPasswordResetDate = ?2 where s.email = ?3" )
    void resetPassword(String password, Calendar now, String email);
}
