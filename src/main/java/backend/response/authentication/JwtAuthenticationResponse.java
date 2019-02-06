package backend.response.authentication;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    private final String msg;

    public JwtAuthenticationResponse(String token, String msg) {
        this.token = token;
        this.msg = msg;
    }

    public String getToken() {
        return this.token;
    }

    public String getMsg() {
        return msg;
    }
}
