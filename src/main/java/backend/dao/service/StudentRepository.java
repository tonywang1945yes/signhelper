package backend.dao.service;

import backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Calendar;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findByEmail(String email);

    Student findByName(String name);

    @Modifying
    @Query("update Student s set s.lastLogOutDate = ?1 where s.email = ?2" )
    int updateLastLogoutDate(Calendar now, String email);

    @Modifying
    @Query("update Student s set s.passwordHash=?1, s.lastPasswordResetDate = ?2 where s.email = ?3" )
    int updatePassword(String passwordHash, Calendar now, String email);
}
