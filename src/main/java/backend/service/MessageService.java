package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Administer;
import backend.entity.AssessmentResult;
import backend.entity.Student;
import backend.enums.StudentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class MessageService {
//    @Autowired

    public void updateState(String message,StudentState state){//state只有4种： JUNIOR_PASSED, JUNIOR_FAILED, SENIOR_PASSED, SENIOR_FAILED
        String path="src/main/resources/msgDic";
        File file=new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        switch (state){
            case NULL:return;
            case UNDER_EXAMINED:return;
            case JUNIOR_FAILED:path+="/junior_failed.txt";break;
            case JUNIOR_PASSED:path+="/junior_passed.txt";break;
            case SENIOR_FAILED:path+="/senior_failed.txt";break;
            case SENIOR_PASSED:path+="/senior_passed.txt";break;
        }
        try {
            File writeName = new File(path);
            writeName.createNewFile();
            FileWriter writer=new FileWriter(file);
            writer.write(message);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
//        HibernateDao<Administer> dao = new HibernateDao<>(new Administer());
//        Administer administer=dao.getAllObjects().get(0);
//        administer.setMessage(message);
//        dao.update(administer);//dao.update()方法返回DatabaseRM枚举类的结果，不需要用下面的判断方式
    }

    public String getMessage(){
        HibernateDao<Administer> dao = new HibernateDao<>(new Administer());
        Administer administer=dao.getAllObjects().get(0);
        return administer.getMessage();
    }

    public void confirmSecondTestAttendance(String email, boolean willing){
        HibernateDao<AssessmentResult> dao = new HibernateDao<>(new AssessmentResult());
        String sql = "SELECT r FROM AssessmentResult.r WHERE r.email = \'"+email+"\'";
        AssessmentResult r = dao.executeQuerySql(sql).get(0);
        r.setAttendSecondTest(willing);
        dao.update(r);
    }
}
