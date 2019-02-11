package backend.response.emailVarification;

public class EmailResponse {
    boolean succeed;

    public EmailResponse(boolean succeed) {
        this.succeed = succeed;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }
}
