package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
import backend.enums.StudentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static backend.enums.StudentState.*;
import static backend.enums.StudentState.JUNIOR_PASSED;

@Service
public class StuListService {

    @Autowired
    StudentRepository repository;

    public Student[] getListByState(int from,int page){
        StudentState state =JUNIOR_PASSED;
        switch (from){
            case 0:state = JUNIOR_PASSED;break;
            case 1:state = JUNIOR_FAILED;break;
            case 2:state = NULL;break;
            case 3:state = SENIOR_PASSED;break;
            case 4:state = SENIOR_FAILED;break;
            case 5:state = JUNIOR_PASSED;break;
        }
        int count=0;//用于标记这是第几个学生
        Student[] students=new Student[15];
        List<Student> allstu;
        if(from ==2){
            allstu = repository.findAll();
        }
        else {
            allstu = repository.findAllByStudentState(state);
        }
        for(int i=(page-1)*15;i<page*15;i++){
            students[count]=allstu.get(i);
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
        return students;
    }

    public int getStuNumber(int from){
        StudentState state =JUNIOR_PASSED;
        switch (from){
            case 0:state = JUNIOR_PASSED;break;
            case 1:state = JUNIOR_FAILED;break;
            case 2:state = NULL;break;
            case 3:state = SENIOR_PASSED;break;
            case 4:state = SENIOR_FAILED;break;
            case 5:state = JUNIOR_PASSED;break;
        }
        int count=0;//记录学生个数
        List<Student> allstu=repository.findAllByStudentState(state);

        return allstu.size();
    }

    public Student[] findStudent(String name){
        List<Student> studentList = repository.findAllByName(name);
        Student[] students=new Student[studentList.size()];
        for(int i=0;i<studentList.size();i++){
            students[i] = studentList.get(i);
        }
        return students;
    }

    public  int getNameNumber(String name){
        return findStudent(name).length;
    }
}
