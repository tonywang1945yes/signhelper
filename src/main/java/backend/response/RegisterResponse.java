package backend.response;


public class RegisterResponse {
    boolean isSucceed;

    /**
     *
     * @param isSucceed    是否注册成功
     */
    public RegisterResponse(boolean isSucceed){
        this.isSucceed = isSucceed;
    }
}
