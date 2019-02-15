package backend.entity.application;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name  = "tbl_activity")
public class Activity {
    @Id
    @JsonIgnore
    private Long id;

//    @ManyToOne(targetEntity = ApplForm.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    @JsonIgnore
    private Long formId;

    private String organization;
    private String award;

    @Temporal(TemporalType.DATE)
    private Calendar attendingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
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

    public Calendar getAttendingDate() {
        return attendingDate;
    }

    public void setAttendingDate(Calendar attendingDate) {
        this.attendingDate = attendingDate;
    }
}
