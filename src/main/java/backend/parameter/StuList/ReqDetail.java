package backend.parameter.StuList;

import backend.enums.StudentState;

public class ReqDetail {

    public StudentState state;
    public int page;
    public String name;

    public ReqDetail(StudentState state,int page){
        this.state=state;
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

    public StudentState getState() {
        return state;
    }
}
