package backend.controller;

import backend.parameter.start.StartParameter;
import backend.service.AddMajorService;
import backend.service.SetDDLService;
import backend.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController()
@RequestMapping(value = "/start")
public class StartController {

    @Autowired
    StartService startService;

    @Autowired
    AddMajorService addMajorService;

    @Autowired
    SetDDLService setDDLService;

    @GetMapping(value = "/start") //两个start吗
    public void start(StartParameter parameter){
        startService.start();
        for (String[] major : parameter.getMajors()){
            addMajorService.add(major);
        }
        setDDLService.setDDL(parameter.getCalendar());
    }

}
