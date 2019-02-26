package backend.controller;


import backend.entity.Major;
import backend.parameter.setMajor.SetMajorParameter;
import backend.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return (Major[]) majorService.getMajors().toArray();
    }

}
