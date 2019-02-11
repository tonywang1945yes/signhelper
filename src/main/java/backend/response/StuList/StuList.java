package backend.response.StuList;

import backend.entity.Student;

import java.util.List;

public class StuList {

    public List<Student> stuList;
    public int total;

    public StuList(List<Student> list,int number){
        stuList=list;
        total=number;
    }

}
