package backend.controller;

import backend.parameter.StuList.ReqDetail;
import backend.response.StuList.StuList;
import backend.service.StuListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value="/list")
public class ListController {
    @Autowired
    StuListService service;


    @RequestMapping(value = "/acquirement",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public StuList getList(@RequestBody ReqDetail reqDetail){
        if(reqDetail.getName()==null){
            return  new StuList(service.getListByState(reqDetail.getState(),reqDetail.getPage()),service.getStuNumber(reqDetail.getState()));
        }
        else{
            return new StuList(service.findStudent(reqDetail.getName()),service.getNameNumber(reqDetail.getName()));
        }
    }
}
