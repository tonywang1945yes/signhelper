package backend.parameter.loginPrameter;

public class LoginParameter {
    private String emailAddress;
    private String password;
    private String captcha;

    public LoginParameter(String emailAddress, String password, String captcha) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.captcha = captcha;
    }

    public LoginParameter() {
    }


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
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
