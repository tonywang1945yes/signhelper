package backend.entity.application;

import backend.entity.Student;
import backend.parameter.application.ApplFormParameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@Table(name = "tbl_application_form")
public class ApplForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
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
    @NotNull
    private String visaNum;

    @Column(name = "identity_num")
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

    @Column(name = "school_attended")
    @Embedded
    private SchoolAttended schoolAttended;

    @Column(name = "gsat_result")
    @Embedded
    private GSATresult gsatResult;


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

    public SchoolAttended getSchoolAttended() {
        return schoolAttended;
    }

    public void setSchoolAttended(SchoolAttended schoolAttended) {
        this.schoolAttended = schoolAttended;
    }

    public GSATresult getGsatResult() {
        return gsatResult;
    }

    public void setGsatResult(GSATresult gsatResult) {
        this.gsatResult = gsatResult;
    }

    public ApplForm() {
        setStudentId("");
        setIdentityNum("");
        setVisaNum("");
        setFirstName("");
        setLastName("");
        setHighSchool("");
        setAddress("");
        setAcceptAssignment(false);
        setArtOrSci(0);
        setGraduationYear("");
        setPostalCode("");
        setSex(0);
    }

    public void updateInfo(Student s) {
        setStudentId(s.getEmail());
        setAddress(s.getAddress());
        setBirthDate((Calendar) s.getBirthDate().clone());
        setFirstName(s.getName().substring(0, 1));
        setLastName(s.getName().substring(1));
        setHighSchool(s.getHighSchool());
        setVisaNum(s.getVisaNum());
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
        setSchoolAttended(p.getSchoolAttended());
        setGsatResult(p.getGsatResult());
    }

}
