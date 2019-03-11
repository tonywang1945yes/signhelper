package backend.response.StuList;

import backend.entity.Student;
import backend.entity.application.ApplForm;

import java.util.List;

public class StuList {

    public ApplForm[] stuList;
    public int total;

    public StuList(ApplForm[] list,int number){
        stuList=list;
        total=number;
    }

}
