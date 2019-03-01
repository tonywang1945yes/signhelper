package backend.controller;

import backend.parameter.emailVerification.SendMailParameter;
import backend.parameter.emailVerification.SetSendAddressParam;
import backend.parameter.emailVerification.VerifyMailParameter;
import backend.response.BasicResponse;
import backend.service.RegMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    RegMailService service;

    @PostMapping(value = "/adminEmailSet", consumes = {"application/json", "application/xml"})
    public void setEmail(@RequestBody SetSendAddressParam param) {
        service.setEmailPermission(param.getEmailAddress(), param.getAdmission());
    }

    @RequestMapping(value = "/send-verification-email",
            method = RequestMethod.POST)
    public BasicResponse sendMail(@RequestBody SendMailParameter param) {
        if (service.checkIdentity(param.getEmailAddress(), param.getIdCardNumber())) {
            try {
                service.sendVerificationCode(param.getEmailAddress());
                return new BasicResponse(true, "");
            } catch (Exception e) {
                e.printStackTrace();
                return new BasicResponse(false, "发送失败");
            }
        }
        return new BasicResponse(false, "身份验证不通过");
    }

    @RequestMapping(value = "/verification",
            method = RequestMethod.POST)
    @ResponseBody
    public BasicResponse checkCodeAndReset(@RequestBody VerifyMailParameter param) {
        if (service.checkCodeAndReset(param.getEmail(), param.getPassword(), param.getCode())) {
            return new BasicResponse(true, "");
        } else {
            return new BasicResponse(false, "重置失败");
        }
    }

    @RequestMapping(value = "/hint",
            method = RequestMethod.GET)
    public void remind() throws Exception {
        service.groupSendMail();
    }

}
