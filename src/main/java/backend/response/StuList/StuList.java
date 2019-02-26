package backend.response.StuList;

import backend.entity.Student;

import java.util.List;

public class StuList {

    public Student[] stuList;
    public int total;

    public StuList(Student[] list,int number){
        stuList=list;
        total=number;
    }

}
