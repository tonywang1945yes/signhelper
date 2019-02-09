package backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_result")
public class AssessmentResult {
    @Id
    private Long id;

    @NotNull
    private String email;

    private Boolean firstAssessment;

    private Boolean attendSecondTest;

    private Boolean secondAssessment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getFirstAssessment() {
        return firstAssessment;
    }

    public void setFirstAssessment(Boolean firstAssessment) {
        this.firstAssessment = firstAssessment;
    }

    public Boolean getAttendSecondTest() {
        return attendSecondTest;
    }

    public void setAttendSecondTest(Boolean attendSecondTest) {
        this.attendSecondTest = attendSecondTest;
    }

    public Boolean getSecondAssessment() {
        return secondAssessment;
    }

    public void setSecondAssessment(Boolean secondAssessment) {
        this.secondAssessment = secondAssessment;
    }
}
