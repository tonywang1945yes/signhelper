package backend.controller;

import backend.service.LogoutService;
import backend.util.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LogoutController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private LogoutService service;

//    //有final时必须有构造函数，暂时分不出好坏
//    public LogoutController(@Value("Authentication") String tokenHeader, JwtToken jwtToken, LogoutService service) {
//        this.tokenHeader = tokenHeader;
//        this.jwtToken = jwtToken;
//        this.service = service;
//    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(HttpServletRequest request) {
        final String requestHeader = request.getHeader(this.tokenHeader);
        String authToken = requestHeader.substring(7);
        String username = jwtToken.getUsernameFromToken(authToken);
        service.updateLastLogoutDate(username);
    }
}
