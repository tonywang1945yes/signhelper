package backend.controller;


import backend.parameter.loginPrameter.LoginParameter;
import backend.response.loginResponse.LoginResponse;
import backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping(value="/login")
public class LoginController {

    @Autowired
    LoginService service;

    @RequestMapping(value = "/password",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)

    public void createHotel(@RequestBody LoginParameter param,
                            HttpServletRequest request, HttpServletResponse response) {
        System.out.println("username:"+param.username);
        System.out.println("password:"+param.password);
        //response.setHeader("Location", request.getRequestURL().append("/").append(createdHotel.getId()).toString());
    }


}
