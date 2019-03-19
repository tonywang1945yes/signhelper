package backend.dao.service;

import backend.entity.application.ApplForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplFormRepository extends JpaRepository<ApplForm,Long> {

    List<ApplForm> findByStudentId(String studentId);

    List<ApplForm> findByNeedSimplification(Boolean state);

    ApplForm findByIdentityNum(String idNum);

    ApplForm findById(long id);

}
