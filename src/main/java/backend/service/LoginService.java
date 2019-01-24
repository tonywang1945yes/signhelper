package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public  boolean checkPassword(String uname,String pwd){
        HibernateDao<User> dao=new HibernateDao<User>(new User());
        User user=dao.findByKey(uname);
        if(user!=null && user.getPassword().equals(pwd)){
            return true;
        }else {
            return false;
        }
    }
}
