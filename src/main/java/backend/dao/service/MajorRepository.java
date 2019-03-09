package backend.dao.service;

import backend.entity.Major;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major, Long> {

    Major findByName(String major);

    Major findById(long id);

    List<Major> findAll();

}
