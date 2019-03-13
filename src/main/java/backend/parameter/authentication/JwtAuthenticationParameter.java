package backend.parameter.authentication;

import java.io.Serializable;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtAuthenticationParameter implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String username;//在这里用户名即为邮箱
    private String password;
    private String forRole;

    public JwtAuthenticationParameter() {
        super();
    }

    public JwtAuthenticationParameter(String username, String password, String forRole) {
        this.username = username;
        this.password = password;
        this.forRole = forRole;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForRole() {
        return forRole;
    }

    public void setForRole(String forRole) {
        this.forRole = forRole;
    }
}
