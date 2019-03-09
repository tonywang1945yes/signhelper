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

    @Column(name = "name")
    private String name = "";

    @Column(name = "student_number")
    private Integer stuNum = 0;

    @Column(name = "accept_art")
    private Boolean acceptArt;

    @Column(name = "time")
    private Integer time;

    @Column(name = "college")
    private String college;

    @Column(name = "price")
    private Integer price;

    @Column(name = "comment")
    private String comment;

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

    public Boolean getAcceptArt() {
        return acceptArt;
    }

    public void setAcceptArt(Boolean acceptArt) {
        this.acceptArt = acceptArt;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Major(){

    }

    public Major(String name,  Boolean acceptArt, Integer time, String college, Integer price, String comment){
        this.setName(name);
        this.setAcceptArt(acceptArt);
        this.setTime(time);
        this.setCollege(college);
        this.setPrice(price);
        this.setComment(comment);
    }


    public Major(String name, Boolean acceptArt, Integer time, String college, String comment){
        this.setName(name);
        this.setAcceptArt(acceptArt);
        this.setTime(time);
        this.setComment(comment);
        this.setCollege(college);
    }
    public Major(Long id,String name, Boolean acceptArt, Integer time, String college, String comment){
        this.id = id;
        this.setName(name);
        this.setAcceptArt(acceptArt);
        this.setTime(time);
        this.setComment(comment);
        this.setCollege(college);
    }
}
