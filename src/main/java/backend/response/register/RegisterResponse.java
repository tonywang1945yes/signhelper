package backend.response.register;


public class RegisterResponse {
    boolean succeed;

    String msg;

    /**
     * @param succeed 是否注册成功
     */
    public RegisterResponse(boolean succeed, String msg) {
        this.succeed = succeed;
        this.msg = msg;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
