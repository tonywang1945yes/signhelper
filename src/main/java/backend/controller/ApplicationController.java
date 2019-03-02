package backend.controller;

import backend.parameter.application.ApplFormParameter;
import backend.response.BasicResponse;
import backend.response.application.ApplFormResponse;
import backend.service.ApplicationService;
import backend.util.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
            method = RequestMethod.GET)
    public ApplFormResponse getApplication(HttpServletRequest request) {
        String email = getIdFromRequest(request);
        return new ApplFormResponse(service.getApplicationForm(email));
    }

    @RequestMapping(value = "/form",
            method = RequestMethod.POST)
    public BasicResponse sendApplication(@RequestBody ApplFormParameter param, HttpServletRequest request) {
        try {
            String email = getIdFromRequest(request);
            service.updateApplForm(param, email);
            return new BasicResponse(true, "");
        } catch (Exception e) {
            e.printStackTrace();
            return new BasicResponse(false, "更新失败");
        }
    }

    @RequestMapping(value = "/attachment",
            method = RequestMethod.POST)
    public BasicResponse sendAttachment(MultipartHttpServletRequest multiRequest, @Value("${savingPath}") String dest) {
//        if (!service.beforeDDL())
//            return new BasicResponse(false, "超过提交时间");
        String type = multiRequest.getParameter("type");
        String email = getIdFromRequest(multiRequest);
        boolean res = service.sendAttachment(dest, email, type, multiRequest.getFiles("file"));//需要键名为file
        return new BasicResponse(res, res ? "" : "上传失败");

    }

    @RequestMapping(value = "/attachment_check", method = RequestMethod.POST)
    public Map<String, Boolean> hasUploadedAttachment(@RequestBody Map<String, String[]> param, HttpServletRequest request, @Value("${savingPath}") String dest) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("hasUploaded", service.hasUploadedAttachment(dest, getIdFromRequest(request), param.get("types")));
        return result;
    }

    @RequestMapping(value = "/attachment_names", method = RequestMethod.GET)
    public String[] getFileNames(HttpServletRequest request, @Value("${savingPath}") String dest) {
        String email = getIdFromRequest(request);
        return service.getFileNames(dest, email);
    }

    @RequestMapping(value = "/simplify_api",
            method = RequestMethod.POST)
    public Map<String, String> simplifyChars(@RequestBody Map<String, String> param) {
        Map<String, String> res = new HashMap<>();
        res.put("simplifiedChars", param.containsKey("raw") ? service.simplifyChars(param.get("raw")) : "");
        return res;
    }


    private String getIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

}
