package backend.controller;

import backend.dao.service.StudentRepository;
import backend.service.LogoutService;
import backend.util.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@RestController
public class LogoutController {

    private final String tokenHeader;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private LogoutService service;

    //并不明白这里的构造方法意义何在
    public LogoutController(@Value("Authentication") String tokenHeader, JwtToken jwtToken, LogoutService service) {
        this.tokenHeader = tokenHeader;
        this.jwtToken = jwtToken;
        this.service = service;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(HttpServletRequest request) {
        final String requestHeader = request.getHeader(this.tokenHeader);
        String authToken = requestHeader.substring(7);
        String username = jwtToken.getUsernameFromToken(authToken);
        service.updateLastLogoutDate(username);
    }
}
