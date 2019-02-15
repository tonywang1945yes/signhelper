package backend.controller;

import backend.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController()
@RequestMapping(value = "/start")
public class StartController {

    @Autowired
    StartService service;

    @GetMapping(value = "/start") //两个start吗
    public void start(){
        service.start();
    }

}
