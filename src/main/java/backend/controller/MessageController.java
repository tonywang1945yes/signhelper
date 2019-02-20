package backend.controller;


import backend.parameter.SetMessage.MessageParam;
import backend.service.MessageService;
import backend.util.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService service;

    @Autowired
    JwtToken jwtToken;

    @Value("${tokenHeader}")
    String header;

    @RequestMapping(value = "/set",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"})
    public void setmessage(@RequestBody MessageParam param)throws Exception{
        service.updateState(param.getMessage(),param.getState());
    }

    @RequestMapping(value = "/confirmation",
        method = RequestMethod.POST)
    public void confirmSecondTestAttendance(@RequestBody Map<String, Boolean> param, HttpServletRequest request){
        if(!param.containsKey("willAttend")) return;
        JwtToken jwtToken = new JwtToken();
        String token = request.getHeader(header).substring(7);
        String email = jwtToken.getUsernameFromToken(token);
        service.confirmSecondTestAttendance(email, param.get("willAttend"));
    }

}
