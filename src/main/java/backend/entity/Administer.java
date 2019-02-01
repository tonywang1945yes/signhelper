package backend.entity;

import backend.enums.AdministerState;

import javax.persistence.*;

@Entity
@Table(name = "tbl_administer")
public class Administer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    当前状态
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private AdministerState state;

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