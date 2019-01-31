package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Administer;
import backend.enums.AdministerState;
import org.springframework.stereotype.Service;

@Service
public class StartService {
    public void start(){
        HibernateDao<Administer> dao = new HibernateDao<>(new Administer());
        Administer administer = dao.getAllObjects().get(0);
        administer.setState(AdministerState.REGISTERING);
        dao.update(administer);
    }
}
