package backend.response.register;


public class RegisterResponse {
    boolean isSucceed;

    String msg;

    /**
     * @param isSucceed 是否注册成功
     */
    public RegisterResponse(boolean isSucceed, String msg) {
        this.isSucceed = isSucceed;
        this.msg = msg;
    }
}
