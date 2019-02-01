package backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SchoolAttended {
    @Column(name = "primary_school")
    private SchlAtdPeriod primarySchool;

    @Column(name = "junior__middle_school")
    private SchlAtdPeriod juniorMiddleSchool;

    @Column(name = "junior__middle_school")
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
