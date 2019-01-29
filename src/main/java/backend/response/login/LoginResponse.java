package backend.response.login;

public class LoginResponse {
    boolean isSucceed;
    String information;

    public LoginResponse(boolean isSucceed, String information) {
        this.isSucceed = isSucceed;
        this.information = information;
    }
}
