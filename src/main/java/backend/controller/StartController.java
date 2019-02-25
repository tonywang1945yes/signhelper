package backend.controller;

import backend.parameter.start.StartParameter;
import backend.service.AddMajorService;
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
@PreAuthorize("hasRole('ADMIN')")
public class StartController {

    @Autowired
    StartService startService;

    @Autowired
    AddMajorService addMajorService;

    @Autowired
    SetDDLService setDDLService;

    @GetMapping(value = "/start") //两个start吗
    public void start(@RequestBody StartParameter parameter){
//        更新管理员状态
        startService.start();
//        更新专业列表
        Set<String> keySet = parameter.getMajors().keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()){
            String key = it.next();
            Integer value = parameter.getMajors().get(key);
            addMajorService.add(key, value);
        }
//        设置DDL
        setDDLService.setDDL(parameter.getCalendar());
    }

}
