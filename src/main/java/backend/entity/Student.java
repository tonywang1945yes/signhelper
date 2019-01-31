package backend.entity;

import backend.enums.StudentState;
import backend.parameter.register.RegisterParameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "tbl_student")
public class Student {

//    姓名
    @Column(name = "name")
    private String name = "";

//    当前状态
    @Column(name = "state")
    private StudentState studentState;

//    身份证号
    @Column(name = "identity_num")
    private String identityNum;

//    通行证号
    @Column(name = "visa_num")
    private String visaNum;

//    加密密码
    @Column(name = "password_hash")
    private String passwordHash;

//    出生日期
    @Column(name = "birth_date")
    private Calendar birthDate;

//    联系电话
    @Column(name = "tel")
    private String tel;

//    家庭住址
    @Column(name = "address")
    private String address;

//    邮箱地址 -- ID
    @Id
    @Column(name = "email")
    private String email;

//    就读高中
    @Column(name = "highSchool")
    private String highSchool;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentState getStudentState() {
        return studentState;
    }

    public void setStudentState(StudentState studentState) {
        this.studentState = studentState;
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


    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
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

    public Student(String name, StudentState state, String identityNum, String visaNum, String passwordHash, Calendar birthDate, String tel, String address, String email, String highSchool) {
        this.setName(name);
        this.setStudentState(state);
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
