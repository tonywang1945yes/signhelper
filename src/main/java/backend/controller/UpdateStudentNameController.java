package backend.controller;

import backend.Application;
import backend.entity.Student;
import backend.entity.application.ApplForm;
import backend.parameter.stuInfo.NamechangeParam;
import backend.response.ApplFormList.ApplFormList;
import backend.response.BasicResponse;
import backend.service.UpdateStudentNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping(value = "/updateStudentName")
@PreAuthorize("hasRole('ADMIN')")
public class UpdateStudentNameController {

    @Autowired
    UpdateStudentNameService service;

    @GetMapping(value = "/acquirement")
    @ResponseBody
    public ApplFormList getApplFormList(){
        return new ApplFormList(service.getApplFormList());
    }

    @PostMapping(value = "/updating")
    public BasicResponse update(@RequestBody NamechangeParam param){
        return service.update(param.getIdentityNum(),param.getFirstName(),param.getLastName());
    }


}
