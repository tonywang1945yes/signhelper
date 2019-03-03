package backend.controller;


import backend.entity.message.Broadcast;
import backend.entity.message.Message;
import backend.enums.StudentState;
import backend.parameter.SetMessage.MessageParam;
import backend.response.BasicResponse;
import backend.service.MessageService;
import backend.util.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
    public void setmessage(@RequestBody MessageParam param) throws Exception {
        param.setState();
        service.updateTemplate(param.getMessage(), param.getState());
    }

    @RequestMapping(value = "/confirmation",
            method = RequestMethod.POST)
    public void confirmSecondTestAttendance(@RequestBody Map<String, Boolean> param, HttpServletRequest request) {
        if (!param.containsKey("willAttend")) return;
        JwtToken jwtToken = new JwtToken();
        String token = request.getHeader(tokenHeader).substring(7);
        String email = jwtToken.getUsernameFromToken(token);
        service.confirmSecondTestAttendance(email, param.get("willAttend"));
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET)
    public List<Message> getMessageList(HttpServletRequest request) {
        String email = getIdFromRequest(request);
        return service.getMessageList(email);
    }

//    @RequestMapping(value = "/{messageId}",
//            method = RequestMethod.GET)
//    public Message getMessageDetail(@PathVariable Long messageId, HttpServletRequest request) {
//        String email = getIdFromRequest(request);
//        return service.getMessageDetail(messageId, email);
//    }

    //    @GetMapping(value = "/messageSending")
//    public Boolean sendResultMessage() {
//        if (!service.check()) {
//            return false;
//        }
//        service.sendResultMessage();
//        return true;
//    }

    @RequestMapping(value = "/global_broadcast", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public BasicResponse sendGlobalBroadcast(@RequestBody Map<String, String> param) {
        if (service.sendGlobalBroadcast(param.get("title"), param.get("content")))
            return new BasicResponse(true, "");
        return new BasicResponse(false, "发送失败");
    }

    @RequestMapping(value = "/released_messages", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<Broadcast> getReleasedBroadcast() {
        return service.getReleasedBroadcast();
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> updateBroadcast(@RequestBody List<Broadcast> broadcasts) {
        Map<String, Boolean> res = new HashMap<>();
        res.put("succeed", service.updateBroadcast(broadcasts));
        return res;
    }

    @RequestMapping(value = "/template/{type}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String getTemplate(@PathVariable String type) {
        return service.getTemplate(StudentState.valueOf(type));
    }

    @RequestMapping(value = "/template", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public BasicResponse updateTemplate(@RequestBody Map<String, String> param) {
        boolean res = service.updateTemplate(param.get("content"), StudentState.valueOf(param.get("type")));
        return new BasicResponse(res, res ? "" : "更新失败");
    }

    private String getIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        return jwtToken.getUsernameFromToken(token);
    }

}
