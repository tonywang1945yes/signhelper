package backend.controller;


import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import backend.enums.resultMessage.DatabaseRM;
import backend.parameter.register.RegisterParameter;
import backend.response.register.RegisterResponse;
import backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    RegisterService service;

    @RequestMapping(value = "/student",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseBody
    public RegisterResponse register(@RequestBody RegisterParameter param) {
        HibernateDao<Student> dao = new HibernateDao<>(new Student());
        if (service.checkDuplicatedRegister(param.getEmail())) {
            return new RegisterResponse(false, "该邮箱已被注册过");
        }else if(service.checkDuplicatedVisaNum(param.getVisaNum())){
            return new RegisterResponse(false, "通行证号码已被使用过");
        }
        Student student = new Student(param);
        try {
            student.setPasswordHash(new BCryptPasswordEncoder().encode(param.getPassword()));//改用Spring Security内置的BCryptPasswordEncoder加密器
            if(dao.add(student) == DatabaseRM.SUCCESS)
                return new RegisterResponse(true, "");
        } catch (Exception E) {
            E.printStackTrace();
        }
        return new RegisterResponse(false, "注册失败，请稍后再试");
//        Student targetStudent = dao.findByKey(param.getEmail());
//        if (targetStudent != null){
//            return new RegisterResponse(true);
//        }
//        else {
//            return new RegisterResponse(false);
//        }
    }

    @GetMapping("/email_duplication_check")
    public boolean checkDuplicatedRegister(@RequestBody String email){
        return service.checkDuplicatedRegister(email);
    }

    @GetMapping("/MTPNumber_duplication_check")
    public boolean checkDuplicatedMTPNumber(@RequestBody String visaNum){
        return service.checkDuplicatedVisaNum(visaNum);
    }



}
