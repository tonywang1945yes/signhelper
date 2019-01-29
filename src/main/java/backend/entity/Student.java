package backend.entity;

import backend.parameter.register.RegisterParameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {


    @Column(name = "name")
    String name = "";

    @Column(name = "identityNum")
    String identityNum;

    @Column(name = "visaNum")
    String visaNum;

    @Column(name = "passwordHash")
    String passwordHash;

    @Column(name = "birthDate")
    String birthDate;

    @Column(name = "tel")
    String tel;

    @Column(name = "address")
    String address;

    @Id
    @Column(name = "email")
    String email;

    @Column(name = "highSchool")
    String highSchool;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getVisaNum() {
        return visaNum;
    }

    public void setVisaNum(String visaNum) {
        this.visaNum = visaNum;
    }


    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }


    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }


    public Student() {
    }

    public Student(String name, String identityNum, String visaNum, String passwordHash, String birthDate, String tel, String address, String email, String highSchool) {
        this.setName(name);
        this.setIdentityNum(identityNum);
        this.setVisaNum(visaNum);
        this.setPasswordHash(passwordHash);
        this.setBirthDate(birthDate);
        this.setTel(tel);
        this.setAddress(address);
        this.setEmail(email);
        this.setHighSchool(highSchool);
    }

    public Student(RegisterParameter parameter) {
        this.setName(parameter.getName());
        this.setIdentityNum(parameter.getIdentityNum());
        this.setVisaNum(parameter.getVisaNum());
//        this.setPasswordHash(passwordHash);
        this.setBirthDate(parameter.getBirthDate());
        this.setTel(parameter.getTel());
        this.setAddress(parameter.getAddress());
        this.setEmail(parameter.getEmail());
        this.setHighSchool(parameter.getHighSchool());
    }


}
