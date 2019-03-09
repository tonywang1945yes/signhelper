package backend.dao.service;

import backend.entity.message.ResultMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultMessageRepository extends JpaRepository<ResultMessage,Long> {

    List<ResultMessage> findAllByEmail(String email);
}
