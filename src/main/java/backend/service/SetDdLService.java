package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.AdministerRepository;
import backend.entity.Administer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class SetDdLService {

    @Autowired
    AdministerRepository administerRepo;

    public void setDDL(Calendar calendar){
        Administer administer = administerRepo.findAll().get(0);
        administer.setDdl(calendar);
        administerRepo.save(administer);
    }
}
