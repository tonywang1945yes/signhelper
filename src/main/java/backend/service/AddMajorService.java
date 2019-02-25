package backend.service;


import backend.dao.service.MajorRepository;
import backend.entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddMajorService {

    @Autowired
    MajorRepository majorRepo;

    public void add(String name, Integer stuNUm){
        majorRepo.save(new Major(name, stuNUm));
    }
}
