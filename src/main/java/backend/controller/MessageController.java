package backend.controller;


import backend.entity.message.Broadcast;
import backend.entity.message.Message;
import backend.enums.StudentState;
import backend.parameter.SetMessage.MessageParam;
import backend.parameter.SetMessage.getMessage;
import backend.response.BasicResponse;
import backend.response.message.MessageResponse;
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

import static backend.enums.StudentState.*;
import static backend.enums.StudentState.JUNIOR_PASSED;

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
    public BasicResponse setmessage(@RequestBody MessageParam param) throws Exception {
        StudentState state;
        switch (param.from) {
            case 0:
                state = JUNIOR_PASSED;
                break;
            case 1:
                state = JUNIOR_FAILED;
                break;
            case 2:
                state = NULL;
                break;
            case 3:
                state = SENIOR_PASSED;
                break;
            case 4:
                state = SENIOR_FAILED;
                break;
            case 5:
                state = JUNIOR_PASSED;
                break;
            default:BasicResponse response = new BasicResponse();response.setSucceed(false);return response;
        }
        return  service.updateTemplate(param.getMessage(),state);
    }

    @PostMapping(value = "/resultMessage")
    public MessageResponse getmessage(@RequestBody getMessage param){
        return service.getMessage(param.getEmail());

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

    @GetMapping(value = "/EnrollmentState")
    public String getState (){
        return service.getAdminState();
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

    @RequestMapping(value = "/released_broadcast", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<Broadcast> getReleasedBroadcast() {
        return service.getReleasedBroadcast();
    }

    @RequestMapping(value = "/global_broadcast",
            method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> updateBroadcast(@RequestBody Map<String, List<Broadcast>> param) {
        Map<String, Boolean> res = new HashMap<>();
        res.put("succeed", service.updateBroadcast(param.get("messages")));
        return res;
    }

    @RequestMapping(value = "/global_broadcast",
            method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteBroadcast(@RequestBody Map<String,List<Long>> param) {
        Map<String, Boolean> res = new HashMap<>();
        res.put("succeed", service.deleteBroadcast(param.get("ids")));
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
        return service.updateTemplate(param.get("content"), StudentState.valueOf(param.get("type")));
//        return new BasicResponse(res, res ? "" : "更新失败");
    }

    private String getIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        return jwtToken.getUsernameFromToken(token);
    }

}
