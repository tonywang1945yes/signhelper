package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.AdministerRepository;
import backend.entity.Administer;
import backend.enums.AdministerState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartService {

    @Autowired
    AdministerRepository administerRepo;

    public void start(){
        administerRepo.deleteAll();
        Administer administer = new Administer();
//        administer.setState(AdministerState.REGISTERING);
        administerRepo.save(administer);
    }
}
