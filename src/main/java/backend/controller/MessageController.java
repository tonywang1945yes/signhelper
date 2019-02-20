package backend.controller;


import backend.parameter.SetMessage.MessageParam;
import backend.response.Message.Message;
import backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService service;

    @RequestMapping(value = "/set",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"})
    public void setmessage(@RequestBody MessageParam param)throws Exception{
        service.updateState(param.getMessage(),param.getState());
    }

}
