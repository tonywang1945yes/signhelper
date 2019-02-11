package backend.controller;

import backend.parameter.authentication.JwtAuthenticationParameter;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticationControllerTest {

    @Test
    public void createAuthenticationToken() {
        JwtAuthenticationParameter parameter = new JwtAuthenticationParameter();
        parameter.setUsername("x950031@gmail.com");
        parameter.setPassword("qwerasdf");
        parameter.setCaptcha("xxx");

    }

    @Test
    public void refreshAndGetAuthenticationToken() {
    }

    @Test
    public void handleAuthenticationException() {
    }
}