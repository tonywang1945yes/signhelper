package backend.controller;

import backend.parameter.start.StartParameter;
import backend.service.AddMajorService;
import backend.service.SetDDLService;
import backend.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Set;

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
    public void start(@RequestBody StartParameter parameter){
        startService.start();
        Set<String> keySet = parameter.getMajors().keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()){
            String key = it.next();
            Integer value = parameter.getMajors().get(key);
            addMajorService.add(key, value);
        }
        setDDLService.setDDL(parameter.getCalendar());
    }

}
