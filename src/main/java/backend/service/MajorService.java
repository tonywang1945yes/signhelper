package backend.service;


import backend.dao.service.MajorRepository;
import backend.entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MajorService {

    @Autowired
    MajorRepository majorRepo;

    public void add(String name, Integer stuNum, Boolean acceptArt, Integer time, String college, Integer price, String comment){
        majorRepo.save(new Major(name, stuNum, acceptArt, time, college, price, comment));
    }

    public List<Major> getMajors(){
        return majorRepo.findAll();
    }

}
