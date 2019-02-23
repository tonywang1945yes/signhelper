package backend.service;


import backend.entity.Major;
import org.springframework.stereotype.Service;

@Service
public class AddMajorService {
    public void add(String[] data){
        Major major = new Major(data[0], data[1]);
    }
}
