package backend.entity;

import backend.enums.AdministerState;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tbl_administer")
public class Administer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//      无意义

    //    当前状态
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private AdministerState state;//    系统状态

    @Column(name = "DDL")
    private Calendar ddl;//     材料上传ddl

    //    编辑待发送消息
    @Column(name = "message")
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdministerState getState() {
        return state;
    }

    public void setState(AdministerState state) {
        this.state = state;
    }

    public Calendar getDdl() {
        return ddl;
    }

    public void setDdl(Calendar ddl) {
        this.ddl = ddl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Administer() {
        setState(AdministerState.REGISTERING);
        setMessage("");
    }

}