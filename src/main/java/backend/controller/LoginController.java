package backend.controller;


import backend.parameter.loginPrameter.LoginParameter;
import backend.response.loginResponse.LoginResponse;
import backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value="/login")
public class LoginController {

    @Autowired
    LoginService service;


    @PostMapping(value="/password")
    public LoginResponse loginByPassword(LoginParameter param){
        if(service.checkPassword(param.username,param.password)){
            return new LoginResponse("token",true);
        }else{
            return new LoginResponse("",false);
        }

    }
}
