package backend.entity.application;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name  = "tbl_activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne(targetEntity = ApplForm.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    @JsonIgnore
    private ApplForm form;

    @Column(length = 50)
    private String organization;

    @Column(length = 50)
    private String award;

    @Temporal(TemporalType.DATE)
    private Calendar attendingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplForm getForm() {
        return form;
    }

    public void setForm(ApplForm form) {
        this.form = form;
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
