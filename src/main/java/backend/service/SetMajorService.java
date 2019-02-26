package backend.service;


import backend.dao.service.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetMajorService {

    @Autowired
    MajorRepository majorRepo;


}
