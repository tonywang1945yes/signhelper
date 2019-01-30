package backend.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LoginServiceTest {
    @Autowired
    LoginService loginService;

    @Test
    public void checkPassword() {
        Assert.assertEquals(loginService.checkPassword("wangruihua","123"),null);
    }
}