package backend.controller;

import backend.entity.application.*;
import backend.enums.SubjectCriteria;
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
        p.setIdCardNumber("x12341234");
        p.setMtpNumber("14725836");
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

        SchAtdPeriod p1 = new SchAtdPeriod();
        p1.setName("海淀区第二小学");
        p1.setRegion("北京市");
        c.set(2005, 9, 1);
        p1.setStartDate((Calendar) c.clone());
        c.set(2011, 6, 30);
        p1.setEndDate((Calendar) c.clone());

        SchAtdPeriod p2 = new SchAtdPeriod();
        p2.setName("洛阳区第一中学");
        p2.setRegion("西安市");
        c.set(2011, 9, 1);
        p2.setStartDate((Calendar) c.clone());
        c.set(2014, 6, 30);
        p2.setEndDate((Calendar) c.clone());

        SchAtdPeriod p3 = new SchAtdPeriod();
        p3.setName("如皋中学");
        p3.setRegion("江苏省");
        c.set(2014, 9, 1);
        p3.setStartDate((Calendar) c.clone());
        c.set(2017, 6, 30);
        p3.setEndDate((Calendar) c.clone());

        p.setPrimarySchool(p1);
        p.setJuniorMiddleSchool(p2);
        p.setSeniorMiddleSchool(p3);

        CustomResult<Integer> results = new CustomResult<Integer>(80, 80, 80, 80, 80);
        CustomResult<Integer> actualLevelPoints = new CustomResult<Integer>(15, 15, 15, 15, 15);
        CustomResult<Double> levelRange = new CustomResult<Double>(6.0, 6.0, 6.0, 6.0, 6.0);
        CustomResult<SubjectCriteria> criteria = new CustomResult<SubjectCriteria>(SubjectCriteria.TOP_CRITERIA, SubjectCriteria.TOP_CRITERIA, SubjectCriteria.TOP_CRITERIA, SubjectCriteria.TOP_CRITERIA, SubjectCriteria.TOP_CRITERIA);

        p.setResults(results);
        p.setActualLevelPoints(actualLevelPoints);
        p.setLevelRange(levelRange);
        p.setSingleSubjectCriteria(criteria);
        p.setTotalLevelPoints(69);
        p.setCriteriaLevel(SubjectCriteria.TOP_CRITERIA);

        p.setPersonalStatement("长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试" +
                "长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试" +
                "长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试" +
                "长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试" +
                "长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试" +
                "长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试" +
                "长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试" +
                "长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试" +
                "长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试" +
                "长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试长文本测试");

        Activity[] activities = new Activity[3];
        Activity a1 = new Activity();
        a1.setOrganization("国立台湾大学");
        a1.setAward("参与奖");
        a1.setAttendingDate(Calendar.getInstance());

        Activity a2 = new Activity();
        a2.setOrganization("九九文教基金会");
        a2.setAward("AMC12数学竞赛");
        a2.setAttendingDate(Calendar.getInstance());

        Activity a3 = new Activity();
        a3.setOrganization("台湾世界展望会");
        a3.setAward("饥饿三十人道救援行动");
        a3.setAttendingDate(Calendar.getInstance());

        activities[0] = a1;
        activities[1] = a2;
        activities[2] = a3;
        p.setActivities(activities);

        FamilyParticularItem[] items = new FamilyParticularItem[2];
        FamilyParticularItem i1 = new FamilyParticularItem();
        i1.setRelationship("父亲");
        i1.setName("黄帝");
        i1.setCompanyName("光电");
        i1.setOccupation("工程师");
        i1.setMobilePhoneNumber("11332244");

        FamilyParticularItem i2 = new FamilyParticularItem();
        i2.setRelationship("母亲");
        i2.setName("乐天");
        i2.setCompanyName("荒唐中学");
        i2.setOccupation("老师");
        i2.setMobilePhoneNumber("11778899");
        items[0] = i1;
        items[1] = i2;
        p.setFamilyParticulars(items);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjA2OTg1MTI1QHFxLmNvbSIsImV4cCI6MTU1MTkxOTgzOCwiaWF0IjoxNTUxMzE1MDM4fQ.6Nkce0A8y4mF-BLZ1EUeIZzF758Hq7x5gNDrVkskqNybOmJXA0EbGSlBPU0GSDtyBvIMTpu9aq9jmo5BW8Gnzg");
        HttpEntity<ApplFormParameter> request = new HttpEntity<ApplFormParameter>(p, headers);
        ApplicationResponse response = this.testRestTemplate.postForObject("/application/form", request, ApplicationResponse.class);
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