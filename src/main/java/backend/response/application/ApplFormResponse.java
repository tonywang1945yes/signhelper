package backend.response.application;

import backend.entity.application.*;
import backend.enums.SubjectCriteria;

import java.util.Arrays;
import java.util.Calendar;

public class ApplFormResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String idCardNumber;
    private String mtpNumber;
    private Calendar birthDate;
    private String highSchool;
    private String address;
    private Boolean needSimplification;
    private Integer sex;
    private String graduationYear;
    private String postalCode;
    private PhoneNumbers phoneNumbers;
    private CurriculumChoices curriculumChoices;
    private Integer artOrSci;
    private Boolean acceptAssignment;
    private SchAtdPeriod primarySchool;
    private SchAtdPeriod juniorMiddleSchool;
    private SchAtdPeriod seniorMiddleSchool;
    private CustomResult<Double> results;
    private CustomResult<Integer> actualLevelPoints;
    private CustomResult<Double> levelRange;
    private CustomResult<SubjectCriteria> singleSubjectCriteria;
    private FamilyParticularItem[] familyParticulars;
    private Activity[] activities;
    private String personalStatement;

    public ApplFormResponse() {

    }

    public ApplFormResponse(ApplForm a) {
        this();
        setEmail(a.getStudentId());
        setFirstName(a.getFirstName());
        setLastName(a.getLastName());
        setAddress(a.getAddress());
        setBirthDate(a.getBirthDate());
        setHighSchool(a.getHighSchool());
        setIdCardNumber(a.getIdentityNum());
        setMtpNumber(a.getVisaNum());
        setNeedSimplification(a.getNeedSimplification());
        setSex(a.getSex());
        setGraduationYear(a.getGraduationYear());
        setPostalCode(a.getPostalCode());
        setPhoneNumbers(a.getPhoneNumbers());
        setCurriculumChoices(a.getCurriculumChoices());
        setArtOrSci(a.getArtOrSci());
        setAcceptAssignment(a.getAcceptAssignment());
        setPrimarySchool(a.getPrimarySchool());
        setJuniorMiddleSchool(a.getJuniorMiddleSchool());
        setSeniorMiddleSchool(a.getSeniorMiddleSchool());
        FamilyParticularItem[] items = new FamilyParticularItem[a.getFamilyParticulars().size()];
        setFamilyParticulars((a.getFamilyParticulars().toArray(items)));
        Activity[] as = new Activity[a.getActivities().size()];
        setActivities(a.getActivities().toArray(as));
        setResults(a.getResults());
        setActualLevelPoints(a.getActualLevelPoints());
        setLevelRange(a.getLevelRange());
        setSingleSubjectCriteria(a.getSingleSubjectCriteria());

        setPersonalStatement(a.getPersonalStatement());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getMtpNumber() {
        return mtpNumber;
    }

    public void setMtpNumber(String mtpNumber) {
        this.mtpNumber = mtpNumber;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
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

    @Override
    public String toString() {
        return "ApplFormResponse{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", mtpNumber='" + mtpNumber + '\'' +
                ", birthDate=" + birthDate +
                ", highSchool='" + highSchool + '\'' +
                ", address='" + address + '\'' +
                ", needSimplification=" + needSimplification +
                ", sex=" + sex +
                ", graduationYear='" + graduationYear + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", curriculumChoices=" + curriculumChoices +
                ", artOrSci=" + artOrSci +
                ", acceptAssignment=" + acceptAssignment +
                ", primarySchool=" + primarySchool +
                ", juniorMiddleSchool=" + juniorMiddleSchool +
                ", seniorMiddleSchool=" + seniorMiddleSchool +
                ", results=" + results +
                ", actualLevelPoints=" + actualLevelPoints +
                ", levelRange=" + levelRange +
                ", singleSubjectCriteria=" + singleSubjectCriteria +
                ", familyParticulars=" + Arrays.toString(familyParticulars) +
                ", activities=" + Arrays.toString(activities) +
                ", personalStatement='" + personalStatement + '\'' +
                '}';
    }
}
