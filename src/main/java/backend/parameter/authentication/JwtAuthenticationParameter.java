package backend.parameter.authentication;

import java.io.Serializable;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtAuthenticationParameter implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String username;//在这里用户名即为邮箱
    private String password;
    private String captcha;

    public JwtAuthenticationParameter() {
        super();
    }

    public JwtAuthenticationParameter(String username, String password, String captcha) {
        this.username = username;
        this.password = password;
        this.captcha = captcha;
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

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
