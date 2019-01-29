package backend.response.loginResponse;

public class LoginResponse {
    int isSucceed;
    String information;

    public LoginResponse(int isSucceed, String information) {
        this.isSucceed = isSucceed;
        this.information = information;
    }
}
