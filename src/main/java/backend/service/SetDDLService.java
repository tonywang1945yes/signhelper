package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Administer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class SetDDLService {

    public void setDDL(Calendar calendar){
        HibernateDao<Administer> dao = new HibernateDao<>(new Administer());
        Administer administer = dao.getAllObjects().get(0);
        administer.setDdl(calendar);
    }
}
