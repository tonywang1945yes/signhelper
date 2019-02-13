package backend.parameter.StuList;

import backend.entity.Student;
import backend.enums.StudentFrom;
import backend.enums.StudentState;
import org.hibernate.criterion.Junction;

import static backend.enums.StudentState.*;

public class ReqDetail {

    public StudentFrom from;
    public StudentState state;
    public int page;
    public String name;

    public ReqDetail(StudentFrom from,int page){
        this.from=from;
        this.page=page;
        this.name=null;

    }

    public ReqDetail(String name){
        this.name=name;
    }

    public int getPage() {
        return page;
    }

    public String getName() {
        return name;
    }

    public StudentFrom getFrom(){
        return this.from;
    }

    public StudentState getState() {
        return state;
    }

//    NULL, UNDER_EXAMINED, JUNIOR_PASSED, JUNIOR_FAILED, SENIOR_PASSED, SENIOR_FAILED
    public void setState(){
        switch (from.getState()){
            case 0:state = JUNIOR_PASSED;break;
            case 1:state = JUNIOR_FAILED;break;
            case 2:state = NULL;break;
            case 3:state = SENIOR_PASSED;break;
            case 4:state = SENIOR_FAILED;break;
            case 5:state = JUNIOR_PASSED;break;
        }
    }
}
