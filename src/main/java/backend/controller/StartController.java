package backend.controller;

import backend.parameter.start.StartParameter;
import backend.service.MajorService;
import backend.service.SetDdLService;
import backend.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Set;

@CrossOrigin
@RestController()
@RequestMapping(value = "/start")
//@PreAuthorize("hasRole('ADMIN')")
public class StartController {

    @Autowired
    StartService startService;

//    @Autowired
//    MajorService majorService;

    @Autowired
    SetDdLService setDdLService;

    @PostMapping(value = "/start",
            consumes = {"application/json", "application/xml"})
    public void start(@RequestBody StartParameter parameter){
//        更新管理员状态
        startService.start();
//        设置DDL
        setDdLService.setDDL(parameter.getDdl());
    }

}
