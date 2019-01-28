package backend.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name="student")
public class Student {

    @Id
    @Column(name="name")
    String name="";

    @Column(name="identityNum")
    String identityNum;

    @Column(name = "visaNum")
    String visaNum;

    @Column(name = "password")
    String password;

    @Column(name = "birthDate")
    String birthDate;

    @Column(name = "tel")
    String tel;

    @Column(name = "address")
    String address;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Student(){}

    public Student(String name, String identityNum, String visaNum, String password, String birthDate, String tel, String address, String email, String highSchool){
        this.setName(name);
        this.setIdentityNum(identityNum);
        this.setVisaNum(visaNum);
        this.setPassword(password);
        this.setBirthDate(birthDate);
        this.setTel(tel);
        this.setAddress(address);
        this.setEmail(email);
        this.setHighSchool(highSchool);
    }




}
