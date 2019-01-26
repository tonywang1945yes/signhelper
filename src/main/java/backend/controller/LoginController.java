package backend.controller;


import backend.parameter.loginPrameter.LoginParameter;
import backend.response.loginResponse.LoginResponse;
import backend.service.LoginService;
import backend.util.captchaUtil.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import backend.util.captchaUtil.Captcha;

import java.io.IOException;

@RestController()
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    LoginService service;

//    @RequestMapping(value = "/password",
//            method = RequestMethod.POST,
//            consumes = {"application/json", "application/xml"},
//            produces = {"application/json", "application/xml"})
//    @ResponseStatus(HttpStatus.CREATED)

//    public void createHotel(@RequestBody LoginParameter param,
//                            HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("emailAddress:"+param.emailAddress);
//        System.out.println("password:"+param.password);
//        //response.setHeader("Location", request.getRequestURL().append("/").append(createdHotel.getId()).toString());
//    }

    @GetMapping(value = "/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        Captcha captcha = new Captcha();
        HttpSession session = request.getSession();
        session.setAttribute("captchaCode", captcha.getCode());
        captcha.write(response.getOutputStream());
    }

    @RequestMapping(value = "/student",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})

    public LoginResponse login(@RequestBody LoginParameter param,
                               HttpServletRequest request) {
        String input = param.getCaptcha();
        HttpSession session = request.getSession();
        String captcha = (String) session.getAttribute("captcha");
        if (!input.equalsIgnoreCase(captcha)) {
            return new LoginResponse(0, "验证码错误");
        } else {
            if (!service.checkPassword(param.getEmailAddress(), param.getPassword())) {
                return new LoginResponse(0, "密码错误");
            } else {
                return new LoginResponse(1, "");
            }
        }
    }


}
