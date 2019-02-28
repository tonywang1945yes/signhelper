package backend.controller;


import backend.entity.Message;
import backend.parameter.SetMessage.MessageParam;
import backend.service.MessageService;
import backend.util.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService service;

    @Autowired
    JwtToken jwtToken;

    @Value("${jwt.header}")
    String tokenHeader;

    @RequestMapping(value = "/storage",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"})
    public void setmessage(@RequestBody MessageParam param)throws Exception{
        param.setState();
        service.updateTemplate(param.getMessage(),param.getState());
    }

    @RequestMapping(value = "/confirmation",
        method = RequestMethod.POST)
    public void confirmSecondTestAttendance(@RequestBody Map<String, Boolean> param, HttpServletRequest request){
        if(!param.containsKey("willAttend")) return;
        JwtToken jwtToken = new JwtToken();
        String token = request.getHeader(tokenHeader).substring(7);
        String email = jwtToken.getUsernameFromToken(token);
        service.confirmSecondTestAttendance(email, param.get("willAttend"));
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET)
    public Message[] getMessageList(HttpServletRequest request){
        String email = getIdFromRequest(request);
        return service.getMessageList(email);
    }

    @RequestMapping(value = "/{messageId}")
    public Message getMessageDetail(@PathVariable Long messageId, HttpServletRequest request){
        String email = getIdFromRequest(request);
        return service.getMessageDetail(messageId, email);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST)
    public Map<String, Boolean> updateMessagesState(Message[] messages){
        Map<String,Boolean> res = new HashMap<>();
        res.put("succeed",service.updateMessagesState(messages));
        return res;
    }

    private String getIdFromRequest(HttpServletRequest request){
        String token = request.getHeader(tokenHeader).substring(7);
        return jwtToken.getUsernameFromToken(token);
    }

}
