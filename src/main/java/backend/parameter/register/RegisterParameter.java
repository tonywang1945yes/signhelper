package backend.parameter.register;

import java.util.Calendar;

public class RegisterParameter {
    private String name;
    private String IDCardNumber;
    private String password;
    private Calendar birthDate;
    private String tel;
    private String address;
    private String email;
    private String highSchool;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDCardNumber() {
        return IDCardNumber;
    }

    public void setIDCardNumber(String IDCardNumber) {
        this.IDCardNumber = IDCardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


    public RegisterParameter() {

    }

    /**
     * @param name        姓名
     * @param IDCardNumber     签证号
     * @param password    密码
     * @param birthDate   出生日期
     * @param tel         电话
     * @param address     家庭住址
     * @param email       邮箱地址
     * @param highSchool  就读高中
     */
    public RegisterParameter(String name, String IDCardNumber, String password, Calendar birthDate, String tel, String address, String email, String highSchool) {
        this.setName(name);
        this.setIDCardNumber(IDCardNumber);
        this.setPassword(password);
        this.setBirthDate(birthDate);
        this.setTel(tel);
        this.setAddress(address);
        this.setEmail(email);
        this.setHighSchool(highSchool);
    }

}
