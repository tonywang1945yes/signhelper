package backend.response.application;

import backend.entity.application.ApplForm;

import java.util.Calendar;

public class BasicInfoResponse {
    private String firstName;
    private String lastName;
    private String idCardNumber;
    private Calendar birthDate;
    private String highSchool;
    private String address;

    public BasicInfoResponse() {
    }

    public BasicInfoResponse(ApplForm a){
        this();
        setFirstName(a.getFirstName());
        setLastName(a.getLastName());
        setAddress(a.getAddress());
        setBirthDate(a.getBirthDate());
        setHighSchool(a.getHighSchool());
        setIdCardNumber(a.getIdentityNum());
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
}
