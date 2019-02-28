package backend.dao.service;

import backend.entity.application.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Modifying
    @Query(value = "DELETE FROM tbl_activity where form_id = ?1", nativeQuery = true)
    int deleteActivities(Long formId);
}
