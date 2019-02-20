package backend.controller;

import backend.enums.StudentState;
import backend.service.UpdateStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController()
@RequestMapping(value = "/updateStudent")
public class UpdateStudentController {

    @Autowired
    UpdateStudentService service;

    @RequestMapping(value = "/update",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseBody
    public boolean update(String id, StudentState state){
        return service.updateState(id, state);
    }
}
