package backend.dao.service;

import backend.entity.Student;
import backend.entity.application.ApplForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

    List<Student> findByVisaNum(String visaNum);

    List<Student> findByEmail(String email);

}
