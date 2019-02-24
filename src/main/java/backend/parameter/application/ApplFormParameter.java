package backend.parameter.application;

import backend.entity.application.*;

import java.util.Calendar;
import java.util.List;

public class ApplFormParameter {

    private String firstName;

    private String lastName;

    private Boolean needSimplification;

    private Integer sex;

    private Calendar birthDate;

    private String MTPNumber;

    private String IDCardNumber;

    private String highSchool;

    private String graduationYear;

    private String address;

    private String postalCode;

    private PhoneNumbers phoneNumbers;

    private CurriculumChoices curriculumChoices;

    private Integer artOrSci;

    private Boolean acceptAssignment;

    private SchoolAttended schoolAttended;

    private GSATresult gsatResult;

    private Activity[] activities;


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

    public String getMTPNumber() {
        return MTPNumber;
    }

    public void setMTPNumber(String MTPNumber) {
        this.MTPNumber = MTPNumber;
    }

    public String getIDCardNumber() {
        return IDCardNumber;
    }

    public void setIDCardNumber(String IDCardNumber) {
        this.IDCardNumber = IDCardNumber;
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

    public GSATresult getGsatResult() {
        return gsatResult;
    }

    public void setGsatResult(GSATresult gsatResult) {
        this.gsatResult = gsatResult;
    }

    public Boolean getNeedSimplification() {
        return needSimplification;
    }

    public void setNeedSimplification(Boolean needSimplification) {
        this.needSimplification = needSimplification;
    }

    public Activity[] getActivities() {
        return activities;
    }

    public void setActivities(Activity[] activities) {
        this.activities = activities;
    }
}
