package backend.controller;

import backend.Application;
import backend.entity.Student;
import backend.entity.application.ApplForm;
import backend.response.ApplFormList.ApplFormList;
import backend.service.UpdateStudentNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping(value = "/updateStudentName")
public class UpdateStudentNameController {

    @Autowired
    UpdateStudentNameService service;

    @GetMapping(value = "/getApplFormList")
    @ResponseBody
    public ApplFormList getApplFormList(){
        return new ApplFormList(service.getApplFormList());
    }

    @PostMapping(value = "/update")
    public Boolean update(@RequestBody List<ApplForm> list){
        return service.update(list);
    }


}