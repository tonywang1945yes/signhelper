package backend.controller;


import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import backend.parameter.RegisterParameter;
import backend.response.RegisterResponse;
import backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value="/register")
public class RegisterController {

    @Autowired
    RegisterService service;

    @RequestMapping(value = "/student",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseBody
    public RegisterResponse register(@RequestBody RegisterParameter param){
        HibernateDao<Student> dao=new HibernateDao<>(new Student());
        if (service.checkDumplicatedRegister(param.getVisaNum())){
            return new RegisterResponse(false);
        }
        Student student = new Student();
        student.setName(param.getVisaNum());
        student.setPassword(param.getPassword());
        dao.add(student);
        Student targetStudent = dao.findByKey(param.getEmail());
        if (targetStudent != null){
            return new RegisterResponse(true);
        }
        else {
            return new RegisterResponse(false);
        }
    }



}
