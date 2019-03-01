package backend.controller;


import backend.entity.Major;
import backend.parameter.setMajor.SetMajorParameter;
import backend.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping(value = "/majorSetting")
public class MajorController {

    @Autowired
    MajorService majorService;


    @PostMapping(value = "/majorSetting")
    public void addMajor(@RequestBody SetMajorParameter parameter){
        majorService.add(parameter.getName(), parameter.getStuNum(), parameter.getAcceptArt(), parameter.getTime(), parameter.getCollege(), parameter.getPrice(), parameter.getComment());
    }

    @GetMapping(value = "/majorGetting")
    public Major[] getMajors(){
        List<Major> majors = majorService.getMajors();
        Major[] lists = new Major[majors.size()];
        for(int i=0;i<majors.size();i++){
            lists[i] = majors.get(i);
        }
        return lists;
    }

}
