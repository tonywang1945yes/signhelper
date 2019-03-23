package backend.entity.application;

import backend.entity.Student;
import backend.enums.SubjectCriteria;
import backend.parameter.application.ApplFormParameter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

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

//    成绩
    @Embedded
    private CustomResult<Double> results;

//    实得极分
    @Embedded
    private CustomResult<Integer> actualLevelPoints;

//    级距
    @Embedded
    private CustomResult<Double> levelRange;

//    单科标准
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

    @JsonProperty("email")
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

    @JsonProperty("mtpNumber")
    public String getVisaNum() {
        return visaNum;
    }

    public void setVisaNum(String visaNum) {
        this.visaNum = visaNum;
    }

    @JsonProperty("idCardNumber")
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

    public void updateInfo(Student s) {//仅注册时会调用一次，将所有字段设为非null值
        needSimplification = false;
        sex = 1;
        visaNum = "";
        graduationYear = "";
        postalCode = "";
        phoneNumbers = new PhoneNumbers("", "", "");
        curriculumChoices = new CurriculumChoices("", "", "", "");
        artOrSci = 1;
        acceptAssignment = false;

        setStudentId(s.getEmail());
        setFirstName(s.getName().substring(0, 1));
        setLastName(s.getName().substring(1));
        setIdentityNum(s.getIdentityNum());

        setAddress(s.getAddress() == null ? "" : s.getAddress());
        Calendar c = Calendar.getInstance();
        c.set(2000, 1, 1);
        setBirthDate(s.getBirthDate() == null ? c : s.getBirthDate());
        setHighSchool(s.getHighSchool() == null ? "" : s.getHighSchool());
        //没加电话

        phoneNumbers = new PhoneNumbers("", "", "");
        curriculumChoices = new CurriculumChoices("", "", "", "");

        Calendar c1 = Calendar.getInstance();
        int currentYear = c1.get(Calendar.YEAR);
        c1.set(currentYear - 3, Calendar.SEPTEMBER, 1);
        Calendar c2 = Calendar.getInstance();
        c2.set(currentYear, Calendar.JUNE, 30);
        c2.setTimeZone(TimeZone.getDefault());
        setSeniorMiddleSchool(new SchAtdPeriod("", "", (Calendar) c1.clone(), (Calendar) c2.clone()));

        c1.set(Calendar.YEAR, currentYear - 6);
        c2.set(Calendar.YEAR, currentYear - 3);
        setJuniorMiddleSchool(new SchAtdPeriod("", "", (Calendar) c1.clone(), (Calendar) c2.clone()));

        c1.set(Calendar.YEAR, currentYear - 12);
        c2.set(Calendar.YEAR, currentYear - 6);
        setPrimarySchool(new SchAtdPeriod("", "", (Calendar) c1.clone(), (Calendar) c2.clone()));

        results = new CustomResult<Double>(0.0, 0.0, 0.0, 0.0, 0.0);
        actualLevelPoints = new CustomResult<Integer>(0, 0, 0, 0, 0);
        levelRange = new CustomResult<Double>(0.0, 0.0, 0.0, 0.0, 0.0);
        singleSubjectCriteria = new CustomResult<SubjectCriteria>(SubjectCriteria.AVERAGE_CRITERIA,
                SubjectCriteria.AVERAGE_CRITERIA,
                SubjectCriteria.AVERAGE_CRITERIA,
                SubjectCriteria.AVERAGE_CRITERIA,
                SubjectCriteria.AVERAGE_CRITERIA);

        personalStatement = "";
    }

    public void updateInfo(ApplFormParameter p) {//如果能保证传来的参数都不为null，更新后返回的申请表也不会有null值
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
