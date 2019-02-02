package backend.controller;


import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import backend.enums.resultMessage.DatabaseRM;
import backend.parameter.register.RegisterParameter;
import backend.response.register.RegisterResponse;
import backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import backend.util.secureUtil.PasswordHash;

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
        if (service.checkDuplicatedRegister(param.getVisaNum())) {
            return new RegisterResponse(false);
        }
        Student student = new Student(param);
        try {
            student.setPassword(PasswordHash.createHash(param.getPassword()));
            return new RegisterResponse(dao.add(student) == DatabaseRM.SUCCESS);
        } catch (Exception E) {
            E.printStackTrace();
        }
        return new RegisterResponse(false);
//        Student targetStudent = dao.findByKey(param.getEmail());
//        if (targetStudent != null){
//            return new RegisterResponse(true);
//        }
//        else {
//            return new RegisterResponse(false);
//        }
    }


}
