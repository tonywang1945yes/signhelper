package backend.response.application;

import backend.entity.Student;
import backend.entity.application.ApplForm;

import java.util.Calendar;

public class BasicInfoResponse {
    private String firstName;
    private String lastName;
    private String MTPNumber;
    private String IDCardNumber;
    private Calendar birthDate;
    private String highSchool;
    private String graduationYear;
    private String address;

    public BasicInfoResponse() {
    }

    public BasicInfoResponse(ApplForm a){
        this();
        setFirstName(a.getFirstName());
        setLastName(a.getLastName());
        setAddress(a.getAddress());
        setBirthDate(a.getBirthDate());
        setGraduationYear(a.getGraduationYear());
        setHighSchool(a.getHighSchool());
        setIDCardNumber(a.getIdentityNum());
        setMTPNumber(a.getVisaNum());
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
}
