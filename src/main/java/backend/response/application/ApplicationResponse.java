package backend.response.application;

public class ApplicationResponse {
    private Boolean succeed;
    private String msg;

    public ApplicationResponse(Boolean succeed, String msg) {
        this.succeed = succeed;
        this.msg = msg;
    }

    public Boolean getSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
