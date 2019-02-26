package backend.entity;

import backend.enums.StudentState;
import backend.parameter.register.RegisterParameter;
import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tbl_student")
public class Student {

    //    姓名
    private String name = "";

    //    当前状态
    @Column(name = "student_state") //如果属性名与数据表字段名相同可以不标明name
    @Enumerated(EnumType.STRING)//属性为枚举时所需标注，确定目标转化类型
    private StudentState studentState;

    //    身份证号
    @Column(unique = true, length = 50)
    private String identityNum;

    //    通行证号
    @Column(length = 50)
    private String visaNum;

    //    出生日期
    @Temporal(TemporalType.DATE)//映射时将java.util.Calender类转化为java.sql.Date类
    private Calendar birthDate;

    //    联系电话
    @Column(length = 50)
    private String tel;

    //    家庭住址
    private String address;

    //    邮箱地址 -- ID
    @Id
    @Column(name = "email",length = 100)
    private String email;

    //    就读高中
    @Column(length = 200)
    private String highSchool;

    @JoinColumn(name = "form_id")
    private Long applFormId;

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

    public Long getApplFormId() {
        return applFormId;
    }

    public void setApplFormId(Long applFormId) {
        this.applFormId = applFormId;
    }

    public Student() {
    }

    public Student(String name, StudentState state, String identityNum, String visaNum, String passwordHash, Calendar birthDate, String tel, String address, String email, String highSchool) {
        this.setName(name);
        this.setStudentState(state);
        this.setIdentityNum(identityNum);
        this.setVisaNum(visaNum);
        this.setBirthDate(birthDate);
        this.setTel(tel);
        this.setAddress(address);
        this.setEmail(email);
        this.setHighSchool(highSchool);
    }

    public Student(RegisterParameter parameter) {
        this.setName(parameter.getName());
        this.setIdentityNum(parameter.getIdCardNumber());
        this.setStudentState(StudentState.NULL);
        this.setBirthDate(parameter.getBirthDate());
        this.setTel(parameter.getTel());
        this.setAddress(parameter.getAddress());
        this.setEmail(parameter.getEmail());
        this.setHighSchool(parameter.getHighSchool());
    }


}
