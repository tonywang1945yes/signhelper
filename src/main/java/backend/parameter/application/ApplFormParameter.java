package backend.parameter.application;

import backend.entity.application.*;
import backend.enums.SubjectCriteria;

import java.util.Calendar;

public class ApplFormParameter {

    private String firstName;

    private String lastName;

    private Boolean needSimplification;

    private Integer sex;

    private Calendar birthDate;

    private String mtpNumber;

    private String idCardNumber;

    private String highSchool;

    private String graduationYear;

    private String address;

    private String postalCode;

    private PhoneNumbers phoneNumbers;

    private CurriculumChoices curriculumChoices;

    private Integer artOrSci;

    private Boolean acceptAssignment;

    private FamilyParticularItem[] familyParticulars;

    private SchAtdPeriod primarySchool;

    private SchAtdPeriod juniorMiddleSchool;

    private SchAtdPeriod seniorMiddleSchool;

    private CustomResult<Double> results;

    private CustomResult<Integer> actualLevelPoints;

    private CustomResult<Double> levelRange;

    private CustomResult<SubjectCriteria> singleSubjectCriteria;

    private Integer totalLevelPoints;

    private SubjectCriteria criteriaLevel;

    private Activity[] activities;

    private String personalStatement;


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

    public String getMtpNumber() {
        return mtpNumber;
    }

    public void setMtpNumber(String mtpNumber) {
        this.mtpNumber = mtpNumber;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
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

    public FamilyParticularItem[] getFamilyParticulars() {
        return familyParticulars;
    }

    public void setFamilyParticulars(FamilyParticularItem[] familyParticulars) {
        this.familyParticulars = familyParticulars;
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

    public Integer getTotalLevelPoints() {
        return totalLevelPoints;
    }

    public void setTotalLevelPoints(Integer totalLevelPoints) {
        this.totalLevelPoints = totalLevelPoints;
    }

    public SubjectCriteria getCriteriaLevel() {
        return criteriaLevel;
    }

    public void setCriteriaLevel(SubjectCriteria criteriaLevel) {
        this.criteriaLevel = criteriaLevel;
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

    public String getPersonalStatement() {
        return personalStatement;
    }

    public void setPersonalStatement(String personalStatement) {
        this.personalStatement = personalStatement;
    }
}
