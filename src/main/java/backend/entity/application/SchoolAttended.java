package backend.entity.application;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

@Embeddable
public class SchoolAttended {
    @Embedded
    private SchlAtdPeriod primarySchool;

    @Embedded
    private SchlAtdPeriod juniorMiddleSchool;

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
