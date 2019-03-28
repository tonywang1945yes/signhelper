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
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseBody
    public StuList getList(@RequestBody ReqDetail reqDetail){
        if(reqDetail.getName().equals("")){
            if(reqDetail.from!=2) {
                return new StuList(service.getListByState(reqDetail.getFrom(), reqDetail.getPage()), service.getStuNumber(reqDetail.getFrom()), reqDetail.from);
            }
            else{
                return service.getUnderExamed();
            }
        }
        else{
            return new StuList(service.getNameList(reqDetail.getName(),reqDetail.getPage(),reqDetail.getFrom()),service.getNameNumber(reqDetail.getName(),reqDetail.getFrom()),reqDetail.from);
        }
    }

}
