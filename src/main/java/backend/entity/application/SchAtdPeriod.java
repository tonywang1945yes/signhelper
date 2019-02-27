package backend.entity.application;

import backend.enums.SchoolType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Calendar;

@Embeddable
public class SchAtdPeriod {

    @Column(length = 30)
    private String name;

    private String region;

    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Temporal(TemporalType.DATE)
    private Calendar endDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
