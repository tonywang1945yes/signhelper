package backend.entity;

import backend.enums.RoleName;
import backend.parameter.register.RegisterParameter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @Column(length = 40)
    private String name;

    @Column(length = 100)
    private String passwordEncoded;

    @Column(length = 40)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Temporal(TemporalType.DATE)
    private Calendar lastLogoutDate;

    @Temporal(TemporalType.DATE)
    private Calendar lastPasswordResetDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordEncoded() {
        return passwordEncoded;
    }

    public void setPasswordEncoded(String passwordEncoded) {
        this.passwordEncoded = passwordEncoded;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public Calendar getLastLogoutDate() {
        return lastLogoutDate;
    }

    public void setLastLogoutDate(Calendar lastLogoutDate) {
        this.lastLogoutDate = lastLogoutDate;
    }

    public Calendar getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Calendar lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public User() {
    }

    public User(RegisterParameter p){
        this.setName(p.getEmail());
        this.setRoleName(RoleName.ROLE_STUDENT);
        Calendar c1  = Calendar.getInstance();
        c1.add(Calendar.DATE,-2);
        this.setLastLogoutDate(c1);
        this.setLastPasswordResetDate(c1);
    }
}
