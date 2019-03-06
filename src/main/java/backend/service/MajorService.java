package backend.service;


import backend.dao.service.MajorRepository;
import backend.entity.Major;
import backend.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MajorService {

    @Autowired
    MajorRepository majorRepo;

    public Boolean add(String name, Integer stuNum, Boolean acceptArt, Integer time, String college, Integer price, String comment){
        majorRepo.save(new Major(name, stuNum, acceptArt, time, college, price, comment));
        return isExist(name);
    }

    public Boolean delete(String name){
        majorRepo.delete(majorRepo.findByName(name));
        return !isExist(name);
    }

    private Boolean isExist(String name){
        return majorRepo.findByName(name) != null;
    }


    public List<Major> getMajors(){
        return majorRepo.findAll();
    }

}
