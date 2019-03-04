package backend.controller;

import backend.entity.Student;
import backend.entity.User;
import backend.exception.RegisterException;
import backend.parameter.register.RegisterParameter;
import backend.response.BasicResponse;
import backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    RegisterService service;

    @RequestMapping(value = "/",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public BasicResponse register(@RequestBody RegisterParameter param) throws RegisterException {
        User user = new User(param);//student初始化在构造函数中完成，也许用工厂更好？
        user.setPasswordEncoded(new BCryptPasswordEncoder().encode(param.getPassword()));
        Student student = new Student(param);

        service.register(user, student);
        return new BasicResponse(true, "");

    }

    @ExceptionHandler(RegisterException.class)
    public BasicResponse handleRegisterException(RegisterException ex) {
        ex.printStackTrace();
        return new BasicResponse(false, ex.getMessage());
    }

    @GetMapping("/email_duplication_check/{email}")
    public Map<String, Boolean> checkDuplicatedRegister(@PathVariable String email) {
        Map<String, Boolean> res = new HashMap<>();
        res.put("hasDuplication", service.checkDuplicatedRegister(email));
        return res;
    }

    @GetMapping("/MTPNumber_duplication_check/{IDCardNumber}")
    public Map<String, Boolean> checkDuplicatedIDCardNumber(@PathVariable String IDCardNumber) {
        Map<String, Boolean> res = new HashMap<>();
        res.put("hasDuplication", service.checkDuplicatedIDCardNumber(IDCardNumber));
        return res;
    }

    @RequestMapping(value = "/encode/{password}", method = RequestMethod.GET)
    public String encodePassword(@PathVariable String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }


}
