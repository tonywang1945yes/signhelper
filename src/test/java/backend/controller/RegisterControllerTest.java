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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void register() {
        RegisterParameter p = new RegisterParameter();
        p.setName("黄国钊");
        p.setEmail("1206985125@qq.com");
        p.setPassword("asdfg1234");
        p.setHighSchool("北海中学");
        p.setIDCardNumber("A118822770");
        p.setAddress("广西省北海市海城区");
        p.setTel("18577940215");

        Calendar c = Calendar.getInstance();
        c.set(1998,10,12);
        p.setBirthDate((Calendar)c.clone());

        RegisterResponse expected = new RegisterResponse(true,"");

        RegisterResponse response = this.testRestTemplate.postForObject("/register/", p, RegisterResponse.class);
        assertThat(response).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void handleRegisterException() {
    }
//
//    @Test
//    public void checkDuplicatedMTPNumber1() {
//        Map<String,Boolean> response = (Map)this.testRestTemplate.getForObject("/register/MTPNumber_duplication_check/{MTPNumber}",
//                Map.class,"12345678");
//        Map<String, Boolean> expected = new HashMap<>();
//        expected.put("hasDuplication",true);
//        assertThat(response).isEqualToComparingFieldByFieldRecursively(expected);
//    }
//
//    @Test
//    public void checkDuplicatedMTPNumber2() {
//        Map<String,Boolean> response = (Map)this.testRestTemplate.getForObject("/register/MTPNumber_duplication_check/{MTPNumber}",
//                Map.class,"12348765");
//        Map<String, Boolean> expected = new HashMap<>();
//        expected.put("hasDuplication",false);
//        assertThat(response).isEqualToComparingFieldByFieldRecursively(expected);
//    }
//
//    @Test
//    public void checkDuplicatedRegister1() {
//        Map<String,Boolean> response = (Map)this.testRestTemplate.getForObject("/register/email_duplication_check/{email}",
//                Map.class,"superfreeeee@gmail.com");
//        Map<String, Boolean> expected = new HashMap<>();
//        expected.put("hasDuplication",true);
//        assertThat(response).isEqualToComparingFieldByFieldRecursively(expected);
//    }
//
//    @Test
//    public void checkDuplicatedRegister2() {
//        Map<String,Boolean> response = (Map)this.testRestTemplate.getForObject("/register/email_duplication_check/{email}",
//                Map.class,"10000000@gmail.com");
//        Map<String, Boolean> expected = new HashMap<>();
//        expected.put("hasDuplication",false);
//        assertThat(response).isEqualToComparingFieldByFieldRecursively(expected);
//    }
}