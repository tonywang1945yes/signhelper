package backend.parameter.emailVerification;

public class VerifyMailParameter {

    public String emailAddress;
    public String code;


    public VerifyMailParameter(String emailAddress, String code) {
        this.emailAddress = emailAddress;
        this.code = code;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
