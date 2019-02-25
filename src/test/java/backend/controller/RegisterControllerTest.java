package backend.controller;

import backend.parameter.register.RegisterParameter;
import backend.response.register.RegisterResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

//    @Test
//    public void register() {
//        RegisterParameter p = new RegisterParameter();
//        p.setName("");
//        p.setEmail("");
//        p.setPassword("");
//        p.setHighSchool("");
//        p.setId("");
//        p.setAddress("");
//        p.setTel("");
//
//        Calendar c = Calendar.getInstance();
//        c.set(1998,10,12);
//        p.setBirthDate((Calendar)c.clone());
//
//        RegisterResponse expected = new RegisterResponse(true,"");
//
//        RegisterResponse response = this.testRestTemplate.postForObject("/register/student", p, RegisterResponse.class);
//        assertThat(response).isEqualToComparingFieldByFieldRecursively(expected);
//    }

    @Test
    public void handleRegisterException() {
    }

    @Test
    public void checkDuplicatedRegister1() {
        Map<String, String> p = new HashMap<>();
        p.put("MYPNumber", "12345678");
        Map<String,Boolean> response = this.testRestTemplate.getForObject("/register/email_duplication_check", Map.class,p);
        Map<String, Boolean> expected = new HashMap<>();
        expected.put("hasDuplication",true);
        assertThat(response).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void checkDuplicatedMTPNumber() {
    }
}