package backend.entity.application;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Embeddable
public class SchlAtdPeriod {

    @NotNull
    private String school;

    @NotNull
    private String region;

    //    @Column(name = "start_date")
    @NotNull
    private Calendar startDate;

    //    @Column(name = "end_date")
    @NotNull
    private Calendar endDate;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
}
