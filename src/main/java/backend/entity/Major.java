package backend.entity;


import javax.persistence.*;


/**
 * 招生专业，包括专业名称和专业人数
 */
@Entity
@Table(name = "tbl_major")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "")
    private String name = "";

    @Column(name = "student_number")
    private Integer stuNum = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    public Major(){

    }

    public Major(String name, Integer stuNum){
        this.setName(name);
        this.setStuNum(stuNum);
    }

}
