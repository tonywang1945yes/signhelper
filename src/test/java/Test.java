import backend.dao.impl.HibernateDao;
import backend.entity.User;
import backend.service.LoginService;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        System.out.println("hello world");
//        HibernateDao<User> dao1=new HibernateDao<User>(new User());
////        ArrayList<User> userlist=dao1.executeQuerySql("select u from User u where u.id<5");
////        for(User usr:userlist){
////            System.out.println(usr.getUsername());
////        }
//        User u=new User();
//        u.setUsername("wangruihua");
//        u.setPassword("123");
//        u.setBalance(100);
//        dao1.add(u);
        LoginService service=new LoginService();
        boolean isExisted=service.checkPassword("wangruihua","123");
        System.out.println(isExisted);

    }
}
