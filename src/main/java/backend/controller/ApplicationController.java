package backend.controller;

import backend.entity.application.ApplForm;
import backend.parameter.application.ApplFormParameter;
import backend.response.application.ApplicationResponse;
import backend.response.application.BasicInfoResponse;
import backend.service.ApplicationService;
import backend.util.token.JwtToken;
import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
        String email = getIdFromRequest(request);
        ApplForm applForm = service.getApplicationForm(email);
        applForm.updateInfo(param);
        if (service.updateApplFormAndActivities(applForm, Arrays.asList(param.getActivities())))
            return new ApplicationResponse(true, "");
        else return new ApplicationResponse(false, "更新失败");
    }

    @RequestMapping(value = "/attachment",
            method = RequestMethod.POST)
    public ApplicationResponse sendAttachment(HttpServletRequest request, @Value("${savingPath}") String dest) {
        String email = getIdFromRequest(request);
        File target = new File(dest + email);
        if (!target.exists()) target.mkdir();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");//这里似乎需要某部分的名字为files？

        try {
            for (MultipartFile file : files)
                file.transferTo(new File(target, file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
            return new ApplicationResponse(false, "上传失败");
        }
        return new ApplicationResponse(true, "");
    }

    @RequestMapping(value = "/basic_info",
            method = RequestMethod.GET)
    public BasicInfoResponse getBasicInfo(HttpServletRequest request) {
        String email = getIdFromRequest(request);
        return new BasicInfoResponse(service.getApplicationForm(email));
    }

    @RequestMapping(value = "/simplify_api",
            method = RequestMethod.POST)
    public Map<String, String> simplifyChars(@RequestBody Map<String, String> param) {
        Map<String, String> res = new HashMap<>();
        if (!param.containsKey("raw")) {
            res.put("simplifiedChars", "");
            return res;
        }
        StringBuilder raw = new StringBuilder(param.get("raw"));
        StringBuilder simplified = new StringBuilder();
        for (int i = 0; i < raw.length(); i++) {
            //因担心发生词语转换而拆开字符串分开转换，性能有待研究
            simplified.append(HanLP.convertToSimplifiedChinese(raw.substring(i, i + 1)));
        }
        res.put("simplifiedChars", simplified.toString());
        return res;
    }

    private String getIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        return jwtTokenUtil.getUsernameFromToken(token);
    }
}
