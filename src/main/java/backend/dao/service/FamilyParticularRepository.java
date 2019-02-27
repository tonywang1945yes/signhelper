package backend.dao.service;

import backend.entity.application.FamilyParticularItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyParticularRepository extends JpaRepository<FamilyParticularItem, Long> {

}
