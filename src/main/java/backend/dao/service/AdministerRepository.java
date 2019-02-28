package backend.dao.service;

import backend.entity.Administer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministerRepository extends JpaRepository<Administer, String> {

}
