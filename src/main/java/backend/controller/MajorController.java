package backend.controller;


import backend.entity.Major;
import backend.parameter.setMajor.MajorUpdate;
import backend.parameter.setMajor.Majordelete;
import backend.parameter.setMajor.SetMajorParameter;
import backend.parameter.setMajor.UpdateMajorParam;
import backend.response.BasicResponse;
import backend.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping(value = "/majorSetting")
public class MajorController {

    @Autowired
    MajorService majorService;


    @PostMapping(value = "/majorAdding")
    public BasicResponse addMajor(@RequestBody SetMajorParameter parameter){
        Boolean res = majorService.add(parameter.getName(), parameter.getAcceptArt(), parameter.getTime(), parameter.getCollege(), parameter.getComment());
        if (res){
            return new BasicResponse(true, "Succeed.");
        }
        else {
            return new BasicResponse(false, "Fail to add major,please delete first.");
        }
    }

    @PostMapping(value = "/majorChanging")
    public BasicResponse changeMajor(@RequestBody Major major){
        Boolean res = majorService.change(major);
        if (res){
            return new BasicResponse(true, "Succeed.");
        }
        else {
            return new BasicResponse(false, "Fail to add major.");
        }
    }

    @PostMapping(value = "/majorDeleting",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"} )
    @ResponseBody
    public BasicResponse deleteMajor(@RequestBody Majordelete major){
       return majorService.delete(Long.parseLong(String.valueOf(major.getMajorid())));
    }

//    @GetMapping(value = "/majorUpdating")
//    @ResponseBody
//    public BasicResponse updateMajor(@RequestBody UpdateMajorParam major){
//        boolean succ = majorService.update(major.getId(),major.getName(),major.getAcceptArt(),major.getTime(),major.getCollege(),major.getComment());
//        BasicResponse response = new BasicResponse();
//        response.setMsg(succ?"更新完成":"该ID不存在");
//        return response;
//    }

    @GetMapping(value = "/majorUpdate")
    @ResponseBody
    public BasicResponse updatemajor(@RequestBody UpdateMajorParam major){
        boolean succ = majorService.update(major.getMajorid(),major.getName(),major.getAcceptArt(),major.getTime(),major.getCollege(),major.getComment());
        BasicResponse response = new BasicResponse();
        response.setSucceed(succ);
        response.setMsg(succ?"更新完成":"该ID不存在");
        return response;
    }

//    @GetMapping


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
