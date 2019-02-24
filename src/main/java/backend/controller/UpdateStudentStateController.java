package backend.controller;

import backend.enums.StudentState;
import backend.service.UpdateStudentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController()
@RequestMapping(value = "/updateStudentState")
public class UpdateStudentStateController {

    @Autowired
    UpdateStudentStateService service;

    @PostMapping(value = "/updateState")
    @ResponseBody
    public Boolean update(@RequestBody String id, StudentState state){
        return service.updateState(id, state);
    }
}
