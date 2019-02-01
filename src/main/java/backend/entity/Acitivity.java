package backend.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name  = "tbl_activity")
public class Acitivity {
    @Id
    private Long id;

    @ManyToOne(targetEntity = ApplForm.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "email")
    private String studentId;

    private String organization;
    private String award;

    @Temporal(TemporalType.DATE)
    private Calendar participationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Calendar getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(Calendar participationDate) {
        this.participationDate = participationDate;
    }
}
