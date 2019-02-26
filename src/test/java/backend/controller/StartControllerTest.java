package backend.controller;

import backend.entity.Major;
import backend.parameter.application.ApplFormParameter;
import backend.parameter.authentication.JwtAuthenticationParameter;
import backend.parameter.setMajor.SetMajorParameter;
import backend.parameter.start.StartParameter;
import backend.response.application.ApplicationResponse;
import backend.response.authentication.JwtAuthenticationResponse;
import backend.response.register.RegisterResponse;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StartControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void sendMajors(){
        SetMajorParameter p = new SetMajorParameter();
        p.setAcceptArt(true);
        p.setCollege("软件学院");
        p.setComment("三四年级费用为16000");
        p.setId((long)1);
        p.setName("软件工程");
        p.setTime(4);
        p.setStuNum(30);
        p.setPrice(3000);


        JwtAuthenticationParameter user = new JwtAuthenticationParameter();
        user.setUsername("123456789@gmail.com");
        user.setPassword("asdfghjkl");

        JwtAuthenticationResponse response = this.testRestTemplate.postForObject("/auth",user,JwtAuthenticationResponse.class);

        String token = response.getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",
                "Bearer "+token);
        HttpEntity<SetMajorParameter> request = new HttpEntity<SetMajorParameter>(p, headers);
        ApplicationResponse responses = this.testRestTemplate.postForObject("/majorSetting/majorSetting", request,ApplicationResponse.class);
    }
}