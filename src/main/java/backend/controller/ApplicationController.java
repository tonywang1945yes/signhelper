package backend.controller;

import backend.entity.application.ApplForm;
import backend.parameter.application.ApplFormParameter;
import backend.response.application.ApplicationResponse;
import backend.service.ApplicationService;
import backend.util.token.JwtToken;
import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/application")
public class ApplicationController {
    @Autowired
    ApplicationService service;

    @Autowired
    JwtToken jwtTokenUtil;

    @Value("${jwt.header}")
    String tokenHeader;

    @RequestMapping(value = "/form",
            method = RequestMethod.POST)
    public ApplicationResponse sendApplication(@RequestBody ApplFormParameter param, HttpServletRequest request) {
        String header = request.getHeader(tokenHeader);
        String token = header.substring(7);
        String email = jwtTokenUtil.getUsernameFromToken(token);
        ApplForm applForm = service.getApplicationForm(email);
        applForm.updateInfo(param);
        if (service.updateApplFormAndActivities(applForm, param.getActivities()))
            return new ApplicationResponse(true, "");
        else return new ApplicationResponse(false, "更新失败");
    }

    @RequestMapping(value = "/attachment",
            method = RequestMethod.POST)
    public ApplicationResponse sendAttachment(HttpServletRequest request) {
        //上传附件待实现
        return null;
    }

    @RequestMapping(value = "/simplify_api",
            method = RequestMethod.POST)
    public Map<String, String> simplifyChars(@RequestBody Map<String, String> param) {
        Map<String, String> res = new HashMap<>();
        if(!param.containsKey("raw")) {
            res.put("simplifiedChars", "");
            return res;
        }
        StringBuilder raw = new StringBuilder(param.get("raw"));
        StringBuilder simplified = new StringBuilder();
        for(int i=0;i<raw.length();i++){
            //因担心发生词语转换而拆开字符串分开转换，性能有待研究
            simplified.append(HanLP.convertToSimplifiedChinese(raw.substring(i,i+1)));
        }
        res.put("simplifiedChars",simplified.toString());
        return res;

    }
}
