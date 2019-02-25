package backend.entity.application;

import backend.enums.SchoolType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tbl_school_atd")
public class SchAtdPeriod {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

    private String region;

    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Temporal(TemporalType.DATE)
    private Calendar endDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private SchoolType type;

    @ManyToOne(targetEntity = ApplForm.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    @JsonIgnore
    private ApplForm form;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public SchoolType getType() {
        return type;
    }

    public void setType(SchoolType type) {
        this.type = type;
    }

    public ApplForm getForm() {
        return form;
    }

    public void setForm(ApplForm form) {
        this.form = form;
    }
}
