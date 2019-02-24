package backend.controller;


import backend.entity.Student;
import backend.entity.User;
import backend.exception.RegisterException;
import backend.parameter.register.RegisterParameter;
import backend.response.register.RegisterResponse;
import backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.PAData;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController()
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    RegisterService service;

    @RequestMapping(value = "/student",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public RegisterResponse register(@RequestBody RegisterParameter param) throws RegisterException {
        User user = new User(param);//student初始化在构造函数中完成，也许用工厂更好？
        user.setPasswordEncoded(new BCryptPasswordEncoder().encode(param.getPassword()));
        Student student = new Student(param);
        service.register(user, student);
        return new RegisterResponse(true, "");

    }

    @ExceptionHandler(RegisterException.class)
    public RegisterResponse handleRegisterException(RegisterException ex) {
        ex.printStackTrace();
        return new RegisterResponse(false, ex.getMessage());
    }

    @GetMapping("/email_duplication_check")
    public Map<String, Boolean> checkDuplicatedRegister(@RequestBody Map<String, String> param) {
        Map<String, Boolean> res = new HashMap<>();
        res.put("hasDuplication", false);
        if (!param.containsKey("email"))
            return res;
        res.put("hasDuplication", service.checkDuplicatedRegister(param.get("email")));
        return res;
    }

    @GetMapping("/MTPNumber_duplication_check")
    public Map<String, Boolean> checkDuplicatedMTPNumber(@RequestBody Map<String, String> param) {
        Map<String, Boolean> res = new HashMap<>();
        res.put("hasDuplication", false);
        if (!param.containsKey("MTPNumber"))
            return res;
        res.put("hasDuplication", service.checkDuplicatedVisaNum(param.get("MTPNumber")));
        return res;
    }

}
