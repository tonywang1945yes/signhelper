package backend.service;


import backend.entity.Major;
import org.springframework.stereotype.Service;


@Service
public class AddMajorService {
    public void add(String name, Integer stuNUm){
        Major major = new Major(name, stuNUm);
    }
}
