package backend.dao.service;

import backend.entity.Administer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministerRepository extends JpaRepository<Administer, String> {

}
