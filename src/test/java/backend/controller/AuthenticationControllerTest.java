package backend.controller;

import backend.parameter.authentication.JwtAuthenticationParameter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerTest {

    @Test
    public void createAuthenticationToken() {
        JwtAuthenticationParameter parameter = new JwtAuthenticationParameter();
        parameter.setUsername("x950031@gmail.com");
        parameter.setPassword("qwerasdf");
        parameter.setForRole("STUDENT");

    }

    @Test
    public void refreshAndGetAuthenticationToken() {
    }

    @Test
    public void handleAuthenticationException() {
    }
}