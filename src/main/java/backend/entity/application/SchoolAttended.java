package backend.entity.application;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class SchoolAttended {
//    @Column(name = "primary_school")
    @Embedded
    private SchlAtdPeriod primarySchool;

//    @Column(name = "junior_middle_school")
    @Embedded
    private SchlAtdPeriod juniorMiddleSchool;

//    @Column(name = "senior_middle_school")
    @Embedded
    private SchlAtdPeriod seniorMiddleSchool;

    public SchlAtdPeriod getPrimarySchool() {
        return primarySchool;
    }

    public void setPrimarySchool(SchlAtdPeriod primarySchool) {
        this.primarySchool = primarySchool;
    }

    public SchlAtdPeriod getJuniorMiddleSchool() {
        return juniorMiddleSchool;
    }

    public void setJuniorMiddleSchool(SchlAtdPeriod juniorMiddleSchool) {
        this.juniorMiddleSchool = juniorMiddleSchool;
    }

    public SchlAtdPeriod getSeniorMiddleSchool() {
        return seniorMiddleSchool;
    }

    public void setSeniorMiddleSchool(SchlAtdPeriod seniorMiddleSchool) {
        this.seniorMiddleSchool = seniorMiddleSchool;
    }
}
