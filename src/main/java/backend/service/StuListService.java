package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.ApplFormRepository;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
import backend.entity.application.ApplForm;
import backend.enums.StudentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static backend.enums.StudentState.*;
import static backend.enums.StudentState.JUNIOR_PASSED;

@Service
public class StuListService {

    @Value("${savingPath}")
    String savingpath;

    @Autowired
    ApplicationService service;

    @Autowired
    StudentRepository repository;

    @Autowired
    ApplFormRepository ApplyRepo;

    public ApplForm[] getListByState(int from, int page){
        String[] styles= new String[]{"考生照片","其他材料","身份证明","推荐信","学测成绩单"};
        StudentState state ;
        switch (from){
            case 0:state = JUNIOR_PASSED;break;
            case 1:state = JUNIOR_FAILED;break;
            case 2:state = UNDER_EXAMINED;break;
            case 3:state = SENIOR_PASSED;break;
            case 4:state = SENIOR_FAILED;break;
            case 5:state = JUNIOR_PASSED;break;
            default:return null;
        }
        int count=0;//用于标记这是第几个学生
        ApplForm[] applForms=new ApplForm[15];
        List<Student> allstu;
        allstu = repository.findAllByStudentState(state);
        for(int i=(page-1)*15;i<page*15;i++){
            if(count+1>allstu.size()||i+1>allstu.size()){
                break;
            }
            if(!service.hasUploadedAttachment(savingpath,allstu.get(i).getEmail(),styles)){
                continue;
            }
            Student stu = allstu.get(i);
            long applyId=stu.getApplFormId();
            applForms[count] = ApplyRepo.findById(applyId);
            count++;
        }
//        for(int i=0;i<allstu.size();i++){
//            if(i<(page-1)*15+1){
//                continue;//过滤 page1从stu1开始，page2从stu16开始，page3从stu31开始
//            }
//            if(students.size()==15){//一页15个学生
//                break;
//            }
//            else{
//                students.add(allstu.get(i));
//            }
//        }
        return applForms;
    }

    public int getStuNumber(int from){
        String[] styles= new String[]{"考生照片","其他材料","身份证明","推荐信","学测成绩单"};
        StudentState state =JUNIOR_PASSED;
        switch (from){
            case 0:state = JUNIOR_PASSED;break;
            case 1:state = JUNIOR_FAILED;break;
            case 2:state = UNDER_EXAMINED;break;
            case 3:state = SENIOR_PASSED;break;
            case 4:state = SENIOR_FAILED;break;
            case 5:state = JUNIOR_PASSED;break;
        }
        int count=0;//记录学生个数
        List<Student> allstu=repository.findAllByStudentState(state);
        for(int i = 0;i<allstu.size();i++){
            if(!service.hasUploadedAttachment(savingpath,allstu.get(i).getEmail(),styles)){
                continue;
            }
            if(allstu.get(i).getApplFormId()!=null){
                count++;
            }
        }

        return count;
    }

    public ApplForm[] findStudent(String name){
        String[] styles= new String[]{"考生照片","其他材料","身份证明","推荐信","学测成绩单"};
        List<Student> studentList = repository.findAllByName(name);
        ApplForm[] applyfroms=new ApplForm[studentList.size()];
        for(int i=0;i<studentList.size();i++){
            if(!service.hasUploadedAttachment(savingpath,studentList.get(i).getEmail(),styles)){
                continue;
            }
            long applFormId = studentList.get(i).getApplFormId();
            applyfroms[i] = ApplyRepo.findById(applFormId);
        }
        return applyfroms;
    }

    public  int getNameNumber(String name){
        return findStudent(name).length;
    }

    public ApplForm[] getNameList(String name,int page){
        ApplForm[] appls = findStudent(name);
        ApplForm[] finalAppls = new ApplForm[15];
        int pos = 0;

        if(appls.length<=15){
            return appls;  //小于等于15个直接返回
        }
        for(int i = (page-1)*15; i<page*15;i++){
            if(pos+1>appls.length||i+1>appls.length){
                break;
            }
            finalAppls[pos] = appls[i];
            pos++;
        }
        return finalAppls;
    }
}
