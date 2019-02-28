package backend.parameter.emailVerification;

public class SetSendAddressParam {
    private String emailAddress;
    private String admission;

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAdmission() {
        return admission;
    }
}
