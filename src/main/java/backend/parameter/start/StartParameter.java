package backend.parameter.start;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StartParameter {

    private List<String[]> majors;

    private Calendar calendar;

    public List<String[]> getMajors() {
        return majors;
    }

    public void setMajors(List<String[]> majors) {
        this.majors = majors;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public StartParameter(List<String[]> majors, Calendar ddl){
        this.majors = majors;
        calendar = ddl;
    }

}
