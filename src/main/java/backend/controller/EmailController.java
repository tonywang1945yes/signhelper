package backend.controller;

import backend.parameter.emailVerification.SendMailParameter;
import backend.parameter.emailVerification.VerifyMailParameter;
import backend.response.emailVarification.EmailResponse;
import backend.service.RegMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/email")
public class EmailController {


    @Autowired
    RegMailService service;

    @RequestMapping(value = "/send-verification-email",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"})
    public void sendMail(@RequestBody SendMailParameter param) throws Exception {
        service.insertCode(param.getName(), param.getEmailAddress());
        System.out.println("发送成功");
    }

    @RequestMapping(value = "/verification",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseBody
    public EmailResponse verifyCode(@RequestBody VerifyMailParameter param, HttpServletRequest request) {
        if (service.checkCode(param.getEmailAddress(), param.getCode())) {
            return new EmailResponse(true);
        } else {
            return new EmailResponse(false);
        }
    }

    @RequestMapping(value="/remind",
            method = RequestMethod.GET)
    public void remind()throws Exception{
        service.groupSendMail();
    }

}
