package backend.entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_application_form")
public class ApplForm {
    @Id
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private Integer sex;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar birthDate;

    @Column(name = "visa_num", unique = true)
    private String visaNum;

    @Column(name = "identity_num")
    private String identityNum;

    @Column(name = "high_school")
    private String highSchool;

    @Column(name = "graduation_year")
    private String graduationYear;

    private String address;

    @Column(name = "postal_code")
    private String postalCode;

    @Embedded
    private PhoneNumbers phoneNumbers;

    @Embedded
    private CurriculumChoices curriculumChoices;

    @Column(name = "art_or_sci")
    private Integer artOrSci;

    @Embedded
    private Boolean acceptAssignment;

    @Embedded
    private SchoolAttended schoolAttended;

    @OneToMany(mappedBy = "studentId")
    private Set<FamilyParticularItem> familyParticular = new HashSet<FamilyParticularItem>();

    @Embedded
    private GSATresult gsatResult;

    @OneToMany(mappedBy = "studentId")
    private Set<Acitivity> acitivities = new HashSet<Acitivity>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getVisaNum() {
        return visaNum;
    }

    public void setVisaNum(String visaNum) {
        this.visaNum = visaNum;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public PhoneNumbers getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(PhoneNumbers phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public CurriculumChoices getCurriculumChoices() {
        return curriculumChoices;
    }

    public void setCurriculumChoices(CurriculumChoices curriculumChoices) {
        this.curriculumChoices = curriculumChoices;
    }

    public Integer getArtOrSci() {
        return artOrSci;
    }

    public void setArtOrSci(Integer artOrSci) {
        this.artOrSci = artOrSci;
    }

    public Boolean getAcceptAssignment() {
        return acceptAssignment;
    }

    public void setAcceptAssignment(Boolean acceptAssignment) {
        this.acceptAssignment = acceptAssignment;
    }

    public SchoolAttended getSchoolAttended() {
        return schoolAttended;
    }

    public void setSchoolAttended(SchoolAttended schoolAttended) {
        this.schoolAttended = schoolAttended;
    }

    public Set<FamilyParticularItem> getFamilyParticular() {
        return familyParticular;
    }

    public void setFamilyParticular(Set<FamilyParticularItem> familyParticular) {
        this.familyParticular = familyParticular;
    }

    public GSATresult getGsatResult() {
        return gsatResult;
    }

    public void setGsatResult(GSATresult gsatResult) {
        this.gsatResult = gsatResult;
    }

    public Set<Acitivity> getAcitivities() {
        return acitivities;
    }

    public void setAcitivities(Set<Acitivity> acitivities) {
        this.acitivities = acitivities;
    }
}
