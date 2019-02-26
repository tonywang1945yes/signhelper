package backend.controller;


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

    @PostMapping(value = "/setting")
    public void addMajor(@RequestBody SetMajorParameter parameter){
        majorService.add(parameter.getName(), parameter.getStuNum(), parameter.getAcceptArt(), parameter.getTime(), parameter.getCollege(), parameter.getPrice(), parameter.getComment());
    }

}
