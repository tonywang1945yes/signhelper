package backend.entity.application;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name  = "tbl_family_particular")
public class FamilyParticularItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(targetEntity = ApplForm.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    private String formId;

    @NotNull
    private String relationship;

    @NotNull
    private String name;

    @Column(name = "company_name")
    @NotNull
    private String companyName;

    @NotNull
    private String occupation;

    @Column(name = "mobile_phone_number")
    @NotNull
    private String mobilePhoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
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
