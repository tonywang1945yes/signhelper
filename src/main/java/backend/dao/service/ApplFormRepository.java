package backend.dao.service;

import backend.entity.application.ApplForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplFormRepository extends JpaRepository<ApplForm,Long> {

    ApplForm findByStudentId(String studentId);


}
