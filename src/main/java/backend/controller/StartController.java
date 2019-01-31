package backend.controller;

import backend.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/start")
public class StartController {

    @Autowired
    StartService service;

    @GetMapping(value = "/start")
    public void start(){
        service.start();
    }

}
