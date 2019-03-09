package backend.service;


import backend.dao.service.MajorRepository;
import backend.entity.Major;
import backend.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MajorService {

    @Autowired
    MajorRepository majorRepo;
    //修改原因：不知道为什么majorRepo的findByName和findById等方法统统返回Null
    //Stack Overflow上也没找到答案
    //故特此修改delete, add 与Update 放弃isexit方法，放弃change方法


    public Boolean add(String name, Boolean acceptArt, Integer time, String college, String comment){
        List<Major> majors = majorRepo.findAll();
        for(int i=0 ;i<majors.size();i++){
            if(majors.get(i).getName().equals("")){
                Major addMajor =majors.get(i);
                addMajor.setName(name);
                addMajor.setTime(time);
                addMajor.setComment(comment);
                addMajor.setCollege(college);
                addMajor.setAcceptArt(acceptArt);
                majorRepo.save(addMajor);
            }
        }
        Major major =new Major(name,acceptArt,time,college,comment);
        majorRepo.save(major);
        return true;
    }

    public BasicResponse delete(long identityNum){
        BasicResponse response = new BasicResponse();
        Major killmajor = majorRepo.findById(identityNum);

        if(killmajor!=null) {
            killmajor.setName("");
            majorRepo.save(killmajor);
            response.setSucceed(true);
            response.setMsg("删除成功");
            return response;
        }
        response.setSucceed(false);
        response.setMsg("找不到该id");
        return response;
    }

    public Boolean change(Major major){//无法避免名字输错情况。id更可靠
        if (isExist(major.getName())){
//            delete(major.getName());
        }
        majorRepo.save(major);
        return isExist(major.getName());
    }

    public Boolean update(int id,String name ,boolean accept,int time, String college,String comment){

        Major major = majorRepo.findById(id);
        if(major == null) {
            return false;//没有这个id啊
        }
        major.setName(name);
        major.setAcceptArt(accept);
        major.setCollege(college);
        major.setComment(comment);
        major.setTime(time);
        majorRepo.save(major);
        return isExist(name);//一般来说都是false，待审核
    }

    private Boolean isExist(String name){
        return majorRepo.findByName(name) != null;
    }


    public List<Major> getMajors(){  //表没设计好 或者说我垃圾 主键自增一旦删除序号会很奇怪
        List<Major> lists = majorRepo.findAll();
        List<Major> exactMajos = new ArrayList<>();
        for(int i=0;i<lists.size();i++){
           if(!lists.get(i).getName().equals("")){
               exactMajos.add(lists.get(i));
           }
        }
        return exactMajos;
    }

}
