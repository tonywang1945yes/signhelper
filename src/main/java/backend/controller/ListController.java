package backend.controller;

import backend.parameter.StuList.ReqDetail;
import backend.response.StuList.StuList;
import backend.service.StuListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/list")
public class ListController {
    @Autowired
    StuListService service;


    @RequestMapping(value = "/getlist",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public StuList getlist(@RequestBody ReqDetail reqDetail){
        if(reqDetail.getName()==null){
            return  new StuList(service.getListByState(reqDetail.getState(),reqDetail.getPage()),service.getstuNumber(reqDetail.getState()));
        }
        else{
            return new StuList(service.FindStudent(reqDetail.getName()),service.getNameNumber(reqDetail.getName()));
        }
    }
}
