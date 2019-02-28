package backend.dao.service;

import backend.entity.application.FamilyParticularItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FamilyParticularRepository extends JpaRepository<FamilyParticularItem, Long> {

    @Modifying
    @Query(value = "DELETE FROM tbl_family_particular where form_id = ?1", nativeQuery = true)
    int deleteItems(Long formId);
}
