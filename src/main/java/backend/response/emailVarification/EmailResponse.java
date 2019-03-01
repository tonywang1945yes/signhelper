package backend.response.emailVarification;

public class EmailResponse {
    Boolean succeed;
    String msg;

    public EmailResponse(Boolean succeed, String msg) {
        this.succeed = succeed;
        this.msg = msg;
    }

    public EmailResponse() {
    }

    public Boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public Boolean getSucceed() {
        return succeed;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
