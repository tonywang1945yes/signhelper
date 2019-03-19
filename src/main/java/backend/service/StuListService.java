package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.ApplFormRepository;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
import backend.entity.application.ApplForm;
import backend.enums.StudentState;
import javafx.application.Application;
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
        ApplForm[] applForms;
        List<Student> allstu;
        allstu = repository.findAllByStudentState(state);

        List<ApplForm> forms = new ArrayList<>();
        for(int i = 0;i < allstu.size();i++){
            if(!service.hasUploadedAttachment(savingpath,allstu.get(i).getEmail(),styles)){
                continue;
            }
            Student stu = allstu.get(i);
            long applyId=stu.getApplFormId();
            forms.add(ApplyRepo.findById(applyId));
        }

        applForms = new ApplForm[forms.size()];

        for(int i=0;i<applForms.length;i++){
            applForms[i] = forms.get(i);
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

    //全部重构 输入简体 先把表筛选出来 然后比对from 比对姓名。。。。
    public ApplForm[] findStudent(String name,int from){
        StudentState state;
        switch (from){
            case 0:state = JUNIOR_PASSED;break;
            case 1:state = JUNIOR_FAILED;break;
            case 2:state = UNDER_EXAMINED;break;
            case 3:state = SENIOR_PASSED;break;
            case 4:state = SENIOR_FAILED;break;
            case 5:state = JUNIOR_PASSED;break;
            default:return null;
        }
        String[] styles= new String[]{"考生照片","其他材料","身份证明","推荐信","学测成绩单"};
        List<ApplForm> forms = ApplyRepo.findAll();
        List<ApplForm> nameCorrectForms =new ArrayList<>();
        for(int i = 0;i<forms.size();i++) {
            if ((forms.get(i).getFirstName() + forms.get(i).getLastName()).equals(name)) {
                ApplForm form = forms.get(i);
                Student stu = repository.findByIdentityNum(form.getIdentityNum()).get(0);
                if (stu.getStudentState() == state && service.hasUploadedAttachment(savingpath, stu.getEmail(), styles)) {
                    nameCorrectForms.add(forms.get(i));
                }
            }
        }
            ApplForm[]  finalForms = new ApplForm[nameCorrectForms.size()];
            for(int i=0;i<nameCorrectForms.size();i++){
                finalForms[i]=nameCorrectForms.get(i);
            }
            return finalForms;
        }

//        List<Student> studentList = repository.findAllByName(name);
//        ApplForm[] applyfroms=new ApplForm[studentList.size()];
//        for(int i=0;i<studentList.size();i++){
//            if(!service.hasUploadedAttachment(savingpath,studentList.get(i).getEmail(),styles)){
//                continue;
//            }
//            long applFormId = studentList.get(i).getApplFormId();
//            ApplForm form = ApplyRepo.findById(applFormId);
//            Student student = repository.findByIdentityNum(form.getIdentityNum()).get(0);
//            if(student == null||student.getStudentState()!=state){
//                continue;
//            }
//            applyfroms[i] = form;
//        }
//        return applyfroms;


    public  int getNameNumber(String name,int from){
        return findStudent(name,from).length;
    }

    public ApplForm[] getNameList(String name,int page,int from){
        return findStudent(name,from);

    }

}
