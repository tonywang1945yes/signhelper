package backend.response;

public class BasicResponse {
    Boolean succeed;
    String msg;

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

    public BasicResponse() {
    }

    public BasicResponse(Boolean succeed, String msg) {
        this.succeed = succeed;
        this.msg = msg;
    }
}
