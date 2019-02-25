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
public class Pretest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void init1() {
        RegisterParameter p1 = new RegisterParameter();
        p1.setName("蔡尚達");
        p1.setEmail("x950031@gmail.com");
        p1.setPassword("qwerasdf");
        p1.setHighSchool("台北市私立再興中學");
        p1.setId("11223344");
        p1.setAddress("台北市文山區興隆路4段2號");
        p1.setTel("+886905100114");
        Calendar c1 = Calendar.getInstance();
        c1.set(1999, 11, 11);
        p1.setBirthDate((Calendar) c1.clone());

        RegisterResponse expected = new RegisterResponse(true, "");
        RegisterResponse r1 = this.testRestTemplate.postForObject("/register/student", p1, RegisterResponse.class);
        assertThat(r1).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void init2(){
        RegisterParameter p2 = new RegisterParameter();
        p2.setName("藍于涵");
        p2.setEmail("superfreeeee@gmail.com");
        p2.setPassword("qazwsxedc");
        p2.setHighSchool("北一女中");
        p2.setId("87654321");
        p2.setAddress("台北市信義區信義路10號");
        p2.setTel("+886956189008");
        Calendar c2 = Calendar.getInstance();
        c2.set(2000, 4, 16);
        p2.setBirthDate((Calendar) c2.clone());

        RegisterResponse expected = new RegisterResponse(true, "");
        RegisterResponse r2 = this.testRestTemplate.postForObject("/register/student", p2, RegisterResponse.class);
        assertThat(r2).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void init3(){
        RegisterParameter p3 = new RegisterParameter();
        p3.setName("黎德謙");
        p3.setEmail("clv123654@yahoo.com.tw");
        p3.setPassword("qwerfdsa");
        p3.setHighSchool("建國中學");
        p3.setId("12345678");
        p3.setAddress("台北市永和區中和路40號");
        p3.setTel("+886987456123");
        Calendar c3 = Calendar.getInstance();
        c3.set(1999, 10, 9);
        p3.setBirthDate((Calendar) c3.clone());

        RegisterResponse expected = new RegisterResponse(true, "");
        RegisterResponse r3 = this.testRestTemplate.postForObject("/register/student", p3, RegisterResponse.class);
        assertThat(r3).isEqualToComparingFieldByFieldRecursively(expected);
    }
}