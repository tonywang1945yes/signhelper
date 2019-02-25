package backend.parameter.start;

import java.util.Calendar;
import java.util.Map;

public class StartParameter {

    private Map<String, Integer> majors;

    private Calendar calendar;

    public Map<String, Integer> getMajors() {
        return majors;
    }

    public void setMajors(Map<String, Integer> majors) {
        this.majors = majors;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public StartParameter(Map<String, Integer> majors, Calendar ddl){
        this.majors = majors;
        calendar = ddl;
    }

    public StartParameter() {
    }
}
