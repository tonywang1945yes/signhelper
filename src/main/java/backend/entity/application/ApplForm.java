package backend.entity.application;

import backend.entity.Student;
import backend.enums.SchoolType;
import backend.parameter.application.ApplFormParameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbl_application_form")
public class ApplForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    private Boolean needSimplification;

    private Integer sex;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Calendar birthDate;

    @Column(name = "visa_num")
    private String visaNum;

    @Column(name = "identity_num")
    @NotNull
    private String identityNum;

    @Column(name = "high_school")
    @NotNull
    private String highSchool;

    @Column(name = "graduation_year")
    private String graduationYear;

    @NotNull
    private String address;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone_numbers")
    @Embedded
    private PhoneNumbers phoneNumbers;

    @Column(name = "curriculum_choices")
    @Embedded
    private CurriculumChoices curriculumChoices;

    @Column(name = "art_or_sci")
    private Integer artOrSci;

    @Column(name = "accept_assignment")
    private Boolean acceptAssignment;

    @OneToMany(targetEntity = SchAtdPeriod.class, mappedBy = "form", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SchAtdPeriod> schoolPeriods;

    @OneToMany(targetEntity = FamilyParticularItem.class, mappedBy = "form", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FamilyParticularItem> familyParticulars;

    @Column(name = "gsat_result")
    @Embedded
    private GSATresult gsatResult;

    @OneToMany(targetEntity = Activity.class, mappedBy = "form", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Activity> activities;

    @Lob
    private String statement;


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

    public GSATresult getGsatResult() {
        return gsatResult;
    }

    public void setGsatResult(GSATresult gsatResult) {
        this.gsatResult = gsatResult;
    }

    public List<SchAtdPeriod> getPrimarySchools() {
        return getSchoolsByType(SchoolType.PRIMARY);
    }

    public List<SchAtdPeriod> getJuniorMiddleSchools() {
        return getSchoolsByType(SchoolType.JUNIORMIDDLE);
    }

    public List<SchAtdPeriod> getSeniorMiddleSchools() {
        return getSchoolsByType(SchoolType.SENIORMIDDLE);
    }

    private List<SchAtdPeriod> getSchoolsByType(SchoolType type) {
//        return this.getSchoolPeriods().stream()
//                .filter(x -> x.getType()== type).sorted(new Comparator<SchAtdPeriod>() {
//                    @Override
//                    public int compare(SchAtdPeriod o1, SchAtdPeriod o2) {
//                        return o1.getStartDate().before(o2.getStartDate())? 1:-1;
//                    }
//                }).collect(Collectors.toList());
        return this.getSchoolPeriods().stream()
                .filter(x -> x.getType() == type)
                .sorted((o1, o2) -> o1.getStartDate().before(o2.getStartDate()) ? 1 : -1)
                .collect(Collectors.toList());
    }

    public List<SchAtdPeriod> getSchoolPeriods() {
        return schoolPeriods;
    }

    public void setSchoolPeriods(List<SchAtdPeriod> schoolPeriods) {
        this.schoolPeriods = schoolPeriods;
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

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public ApplForm() {
//        setStudentId("");
//        setIdentityNum("");
//        setVisaNum("");
//        setFirstName("");
//        setLastName("");
//        setHighSchool("");
//        setAddress("");
//        setAcceptAssignment(false);
//        setArtOrSci(0);
//        setGraduationYear("");
//        setPostalCode("");
//        setSex(0);
    }

    public void updateInfo(Student s) {
        setStudentId(s.getEmail());
        setAddress(s.getAddress());
        setBirthDate((Calendar) s.getBirthDate().clone());
        setFirstName(s.getName().substring(0, 1));
        setLastName(s.getName().substring(1));
        setHighSchool(s.getHighSchool());
        setIdentityNum(s.getIdentityNum());
    }

    public void updateInfo(ApplFormParameter p) {
        setFirstName(p.getFirstName());
        setLastName(p.getLastName());
        setNeedSimplification(p.getNeedSimplification());
        setSex(p.getSex());
        setIdentityNum(p.getIDCardNumber());
        setVisaNum(p.getMTPNumber());
        setArtOrSci(p.getArtOrSci());
        setPostalCode(p.getPostalCode());
        setGraduationYear(p.getGraduationYear());
        setAcceptAssignment(p.getAcceptAssignment());
        setHighSchool(p.getHighSchool());
        setBirthDate(p.getBirthDate());
        setAddress(p.getAddress());
        setCurriculumChoices(p.getCurriculumChoices());
        setPhoneNumbers(p.getPhoneNumbers());
        setGsatResult(p.getGsatResult());
        setSchoolPeriods(new ArrayList<SchAtdPeriod>(Arrays.asList(p.getSchAtdPeriods())));
        setStatement(p.getStatement());
    }

}
