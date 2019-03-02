package backend.entity.application;

import backend.entity.Student;
import backend.enums.SubjectCriteria;
import backend.parameter.application.ApplFormParameter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "tbl_application_form")
public class ApplForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String studentId;

    @Column(length = 10)
    @NotNull
    private String firstName;

    @Column(length = 20)
    @NotNull
    private String lastName;

    private Boolean needSimplification;

    private Integer sex;

    @Temporal(TemporalType.DATE)
    private Calendar birthDate;

    @Column(length = 30)
    private String visaNum;

    @Column(length = 30)
    @NotNull
    private String identityNum;

    @Column(length = 80)
    private String highSchool;

    @Column(length = 15)
    private String graduationYear;

    private String address;

    @Column(length = 15)
    private String postalCode;

    @Embedded
    private PhoneNumbers phoneNumbers;

    @Embedded
    private CurriculumChoices curriculumChoices;

    private Integer artOrSci;

    private Boolean acceptAssignment;

    @Embedded
    private SchAtdPeriod primarySchool;

    @Embedded
    private SchAtdPeriod juniorMiddleSchool;

    @Embedded
    private SchAtdPeriod seniorMiddleSchool;

    @OneToMany(targetEntity = FamilyParticularItem.class, mappedBy = "form", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FamilyParticularItem> familyParticulars;

    @Embedded
    private CustomResult<Double> results;

    @Embedded
    private CustomResult<Integer> actualLevelPoints;

    @Embedded
    private CustomResult<Double> levelRange;

    @Embedded
    private CustomResult<SubjectCriteria> singleSubjectCriteria;

    @OneToMany(targetEntity = Activity.class, mappedBy = "form", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Activity> activities;

    @Lob
    private String personalStatement;


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

    public Boolean getNeedSimplification() {
        return needSimplification;
    }

    public void setNeedSimplification(Boolean needSimplification) {
        this.needSimplification = needSimplification;
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

    //    0 is art , 1 is sci
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

    public CustomResult<Double> getResults() {
        return results;
    }

    public void setResults(CustomResult<Double> results) {
        this.results = results;
    }

    public CustomResult<Integer> getActualLevelPoints() {
        return actualLevelPoints;
    }

    public void setActualLevelPoints(CustomResult<Integer> actualLevelPoints) {
        this.actualLevelPoints = actualLevelPoints;
    }

    public CustomResult<Double> getLevelRange() {
        return levelRange;
    }

    public void setLevelRange(CustomResult<Double> levelRange) {
        this.levelRange = levelRange;
    }

    public CustomResult<SubjectCriteria> getSingleSubjectCriteria() {
        return singleSubjectCriteria;
    }

    public void setSingleSubjectCriteria(CustomResult<SubjectCriteria> singleSubjectCriteria) {
        this.singleSubjectCriteria = singleSubjectCriteria;
    }

    public SchAtdPeriod getPrimarySchool() {
        return primarySchool;
    }

    public void setPrimarySchool(SchAtdPeriod primarySchool) {
        this.primarySchool = primarySchool;
    }

    public SchAtdPeriod getJuniorMiddleSchool() {
        return juniorMiddleSchool;
    }

    public void setJuniorMiddleSchool(SchAtdPeriod juniorMiddleSchool) {
        this.juniorMiddleSchool = juniorMiddleSchool;
    }

    public SchAtdPeriod getSeniorMiddleSchool() {
        return seniorMiddleSchool;
    }

    public void setSeniorMiddleSchool(SchAtdPeriod seniorMiddleSchool) {
        this.seniorMiddleSchool = seniorMiddleSchool;
    }

    public List<FamilyParticularItem> getFamilyParticulars() {
        return familyParticulars;
    }

    public void setFamilyParticulars(List<FamilyParticularItem> familyParticulars) {
        this.familyParticulars = familyParticulars;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public String getPersonalStatement() {
        return personalStatement;
    }

    public void setPersonalStatement(String personalStatement) {
        this.personalStatement = personalStatement;
    }

    public ApplForm() {
    }

    public void updateInfo(Student s) {
        setStudentId(s.getEmail());
        setAddress(s.getAddress());
        setBirthDate(s.getBirthDate());
        setFirstName(s.getName().substring(0, 1));
        setLastName(s.getName().substring(1));
        setHighSchool(s.getHighSchool());
        setIdentityNum(s.getIdentityNum());
        //没加电话
    }

    public void updateInfo(ApplFormParameter p) {
        setFirstName(p.getFirstName());
        setLastName(p.getLastName());
        setNeedSimplification(p.getNeedSimplification());
        setSex(p.getSex());
        setIdentityNum(p.getIdCardNumber());
        setVisaNum(p.getMtpNumber());
        setArtOrSci(p.getArtOrSci());
        setPostalCode(p.getPostalCode());
        setGraduationYear(p.getGraduationYear());
        setAcceptAssignment(p.getAcceptAssignment());
        setHighSchool(p.getHighSchool());
        setBirthDate(p.getBirthDate());
        setAddress(p.getAddress());
        setCurriculumChoices(p.getCurriculumChoices());
        setPhoneNumbers(p.getPhoneNumbers());
        setPrimarySchool(p.getPrimarySchool());
        setJuniorMiddleSchool(p.getJuniorMiddleSchool());
        setSeniorMiddleSchool(p.getSeniorMiddleSchool());
        setResults(p.getResults());
        setActualLevelPoints(p.getActualLevelPoints());
        setLevelRange(p.getLevelRange());
        setSingleSubjectCriteria(p.getSingleSubjectCriteria());
        if (p.getFamilyParticulars() != null) {
            List<FamilyParticularItem> items = new ArrayList<FamilyParticularItem>(Arrays.asList(p.getFamilyParticulars()));
            for (FamilyParticularItem item : items)
                item.setForm(this);
            setFamilyParticulars(items);
        }
        if (p.getActivities() != null) {
            List<Activity> activities = new ArrayList<Activity>(Arrays.asList(p.getActivities()));
            for (Activity a : activities)
                a.setForm(this);
            setActivities(activities);
        }
        setPersonalStatement(p.getPersonalStatement());
    }

}
