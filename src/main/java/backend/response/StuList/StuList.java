package backend.response.StuList;

import backend.entity.Student;
import backend.entity.application.ApplForm;

import java.util.List;

public class StuList {

    public ApplForm[] stuList;
    public int total;
    public int studentfrom;

    public int complete;

    public int lack_materials;

    public int lack_upload;

    public int lack_both;

    public StuList(ApplForm[] list,int num,int studentfrom){
        stuList = list;
        total = num;
        this.studentfrom = studentfrom;
    }

}
