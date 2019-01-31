package backend.entity;

import backend.enums.AdministerState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tbl_administer")
public class Administer {

//    当前状态
    @Column(name = "state")
    private AdministerState state;

//    编辑待发送消息
    @Column(name = "message")
    private String message;

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

    public Administer(){
        setState(AdministerState.REGISTERING);
        setMessage("");
    }

}