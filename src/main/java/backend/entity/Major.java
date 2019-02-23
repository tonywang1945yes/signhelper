package backend.entity;


import javax.persistence.*;


/**
 * 招生专业，包括专业名称和专业人数
 */
@Entity
@Table(name = "tbl_major")
public class Major {
    @Column(name = "")
    private String name = "";

    @Column(name = "student_number")
    private String stuNum = "0";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public Major(){

    }

    public Major(String name, String stuNum){
        this.setName(name);
        this.setStuNum(stuNum);
    }

}
