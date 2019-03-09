package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.AdministerRepository;
import backend.entity.Administer;
import backend.response.BasicResponse;
import backend.response.message.DDL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class SetDdLService {

    @Autowired
    AdministerRepository administerRepo;

    public BasicResponse setDDL(Calendar calendar){
        BasicResponse response =new BasicResponse();
        List<Administer> administers = administerRepo.findAll();
        if(administers.size()==0){
            Administer admin = new Administer();
            admin.setDdl(calendar);
            response.setSucceed(true);
            response.setMsg("库内无Admin，新建对象并设置ddl完成");
            administerRepo.save(admin);
        }
        else {
            Administer admin = administers.get(0);
            admin.setDdl(calendar);
            administerRepo.save(admin);
            response.setSucceed(true);
            response.setMsg("Admin对象DDL属性更新完成");
        }
        return response;
    }

    public DDL getDDL(){
        DDL date =new DDL();
        List<Administer> admins = administerRepo.findAll();
        if(admins.size()==0){
            date.setMsg("No admin");
            date.succ = false;
        }
        else if (admins.get(0).getDdl() ==null){
            date.setMsg("Admin exists, No ddl.");
            date.succ = false;
        }
        else{
            date.setDdl(admins.get(0).getDdl());
            date.succ = true;
        }
        return date;
    }
}
