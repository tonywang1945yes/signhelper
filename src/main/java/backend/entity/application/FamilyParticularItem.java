package backend.entity.application;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name  = "tbl_family_particular")
public class FamilyParticularItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = ApplForm.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    private ApplForm form;

    @NotNull
    @Column(length = 20)
    private String relationship;

    @NotNull
    @Column(length = 20)
    private String name;

    @Column(length = 80)
    @NotNull
    private String companyName;

    @NotNull
    @Column(length = 60)
    private String occupation;

    @Column( length = 40)
    @NotNull
    private String mobilePhoneNumber;

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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }
}
