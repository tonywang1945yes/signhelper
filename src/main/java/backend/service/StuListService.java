package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import backend.enums.StudentState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StuListService {

    public List<Student> getListByState(StudentState state,int page){
        int count=0;//用于标记这是第几个学生
        List<Student> students=new ArrayList<>();
        HibernateDao<Student> dao=new HibernateDao<>(new Student());
        List<Student> allstu=dao.getAllObjects();
        for(int i=0;i<allstu.size();i++){
            if(allstu.get(i).getStudentState()==state){
                count++;
            }
            if(count<(page-1)*15+1){
                continue;//过滤 page1从stu1开始，page2从stu16开始，page3从stu31开始
            }
            if(students.size()==15){//一页15个学生
                break;
            }
            else{
                students.add(allstu.get(i));
            }
        }
        return students;
    }

    public int getStuNumber(StudentState state){
        int count=0;//记录学生个数
        HibernateDao<Student> dao=new HibernateDao<>(new Student());
        List<Student> allstu=dao.getAllObjects();
        for(int i=0;i<allstu.size();i++){
            if(allstu.get(i).getStudentState()==state){
                count++;
            }
        }
        return count;
    }

    public List<Student> FindStudent(String name){
        List<Student> students=new ArrayList<>();
        HibernateDao<Student> dao=new HibernateDao<>(new Student());
        List<Student> allstu=dao.getAllObjects();
        for(int i=0;i<allstu.size();i++){
            if(allstu.get(i).getName().equals(name)){
                students.add(allstu.get(i));
            }
        }
        return students;
    }

    public  int getNameNumber(String name){
        return FindStudent(name).size();
    }
}
