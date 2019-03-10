package backend.controller;

import backend.entity.Major;
import backend.parameter.start.StartParameter;
import backend.response.BasicResponse;
import backend.response.message.DDL;
import backend.service.MajorService;
import backend.service.SetDdLService;
import backend.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController()
@RequestMapping(value = "/DDL")
//@PreAuthorize("hasRole('ADMIN')")
public class StartController {

    @Autowired
    StartService startService;

//    @Autowired
//    MajorService majorService;

    @Autowired
    SetDdLService setDdLService;

    @PostMapping(value = "/setting",
            consumes = {"application/json", "application/xml"})
    @ResponseBody
    public BasicResponse setDDL(@RequestBody StartParameter parameter){
//        设置DDL  //跟重置分开 重置我这边新开一个接口
//        理由：因为不清楚这个流程究竟怎么走，而且如果要修改ddl过于不便。

        return setDdLService.setDDL(parameter.getDdl());
    }

    @GetMapping(value = "/getting")
    @ResponseBody
    public DDL getDDL(){
        return setDdLService.getDDL();
    }


    //留一个restart接口 初始化admin/清空admin，不然
}
