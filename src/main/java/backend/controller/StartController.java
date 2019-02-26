package backend.controller;

import backend.parameter.start.StartParameter;
import backend.service.MajorService;
import backend.service.SetDDLService;
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

    @Autowired
    MajorService majorService;

    @Autowired
    SetDDLService setDDLService;

    @PostMapping(value = "/start",
            consumes = {"application/json", "application/xml"}) //两个start吗
    public void start(@RequestBody StartParameter parameter){
//        更新管理员状态
        startService.start();
//        更新专业列表
        majorService.add(parameter.getName(), parameter.getStuNum(), parameter.getAcceptArt(), parameter.getTime(), parameter.getCollege(), parameter.getPrice(), parameter.getComment());
////        设置DDL
//        setDDLService.setDDL(parameter.getCalendar());
    }

}
