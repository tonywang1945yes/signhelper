package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Administer;
import backend.entity.Student;
import backend.enums.StudentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
//    @Autowired
//    HibernateDao<Administer> dao;
    public void updateState(String message){
        HibernateDao<Administer> dao = new HibernateDao<>(new Administer());
        Administer administer=dao.getAllObjects().get(0);
        administer.setMessage(message);
        dao.update(administer);//dao.update()方法返回DatabaseRM枚举类的结果，不需要用下面的判断方式
    }

    public String getMessage(){
        HibernateDao<Administer> dao = new HibernateDao<>(new Administer());
        Administer administer=dao.getAllObjects().get(0);
        return administer.getMessage();
    }
}
