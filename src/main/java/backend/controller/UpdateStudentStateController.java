package backend.controller;

import backend.enums.StudentState;
import backend.parameter.stuInfo.StatechangeParam;
import backend.response.BasicResponse;
import backend.service.UpdateStudentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController()
@RequestMapping(value = "/updateStudentState")
@PreAuthorize("hasRole('ADMIN')")
public class UpdateStudentStateController {

    @Autowired
    UpdateStudentStateService service;

    @PostMapping(value = "/updating")
    public BasicResponse update(@RequestBody StatechangeParam param){
        return service.updateState(param.getIdentityNum(),param.getFrom());
    }
}
