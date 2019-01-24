package backend.response.loginResponse;

public class LoginResponse {
    String token;
    Boolean isSucceed;

    public LoginResponse(String token, Boolean isSucceed) {
        this.token = token;
        this.isSucceed = isSucceed;
    }
}
