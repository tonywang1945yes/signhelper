package backend.controller;

import backend.entity.application.CurriculumChoices;
import backend.entity.application.GSATresult;
import backend.entity.application.SchAtdPeriod;
import backend.enums.SchoolType;
import backend.parameter.application.ApplFormParameter;
import backend.response.application.ApplicationResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void sendApplication() {
        ApplFormParameter p = new ApplFormParameter();
        p.setFirstName("黄");
        p.setLastName("国钊");
        p.setGraduationYear("2017");
        p.setHighSchool("北海中学");
        p.setIDCardNumber("x12341234");
        p.setMTPNumber("14725836");
        p.setNeedSimplification(false);
        p.setSex(1);
        p.setPostalCode("210046");
        p.setAddress("广西省北海市");
        p.setAcceptAssignment(true);
        p.setArtOrSci(1);

        Calendar c = Calendar.getInstance();
        c.set(1998, 10, 12);
        p.setBirthDate((Calendar) c.clone());

        CurriculumChoices choices = new CurriculumChoices();
        choices.setFirstChoice("计算机");
        choices.setSecondChoice("软件工程");
        choices.setThirdChoice("电子");
        p.setCurriculumChoices(choices);

        GSATresult grade = new GSATresult();
        grade.setChinese(15);
        grade.setEnglish(15);
        grade.setMath(15);
        grade.setSocials(15);
        grade.setSocials(15);
        p.setGsatResult(grade);

        SchAtdPeriod[] periods = new SchAtdPeriod[3];
        SchAtdPeriod p1 = new SchAtdPeriod();
        p1.setName("海淀区第二小学");
        p1.setRegion("北京市");
        p1.setType(SchoolType.PRIMARY);
        c.set(2005, 9, 1);
        p1.setStartDate((Calendar) c.clone());
        c.set(2011, 6, 30);
        p1.setEndDate((Calendar) c.clone());
        periods[0] = p1;

        SchAtdPeriod p2 = new SchAtdPeriod();
        p2.setName("洛阳区第一中学");
        p2.setRegion("西安市");
        p2.setType(SchoolType.JUNIORMIDDLE);
        c.set(2011, 9, 1);
        p2.setStartDate((Calendar) c.clone());
        c.set(2014, 6, 30);
        p2.setEndDate((Calendar) c.clone());
        periods[1] = p2;

        SchAtdPeriod p3 = new SchAtdPeriod();
        p3.setName("如皋中学");
        p3.setRegion("江苏省");
        p3.setType(SchoolType.SENIORMIDDLE);
        c.set(2014, 9, 1);
        p3.setStartDate((Calendar) c.clone());
        c.set(2017, 6, 30);
        p3.setEndDate((Calendar) c.clone());
        periods[2] = p3;

        p.setSchAtdPeriods(periods);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjA2OTg1MTI1QHFxLmNvbSIsImV4cCI6MTU1MTY5Mjc3NiwiaWF0IjoxNTUxMDg3OTc2fQ.lc9Ed_2r1Vk_E1rpJn1dBqUkYWoIJN3cCY887oirSfsTmoqQWGH88Cz8LJ1aRgVlde0eQa3Im32nZDIQUPlXzg");
        HttpEntity<ApplFormParameter> request = new HttpEntity<ApplFormParameter>(p, headers);
        ApplicationResponse response = this.testRestTemplate.postForObject("/application/form", request,ApplicationResponse.class);
        assertThat(response.getSucceed()).isTrue();


    }

    @Test
    public void sendAttachment() {
    }

    @Test
    public void getBasicInfo() {
    }

    @Test
    public void simplifyChars() {
    }
}