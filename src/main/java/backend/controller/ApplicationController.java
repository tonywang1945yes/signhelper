package backend.controller;

import backend.parameter.application.ApplFormParameter;
import backend.response.BasicResponse;
import backend.response.application.ApplFormResponse;
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
import java.util.*;

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
        String studentName = service.getStudentName(email);
        File target = new File(dest + "/" +studentName + "-" + email + "/" + type);
        if (!target.exists())
            target.mkdirs();
        else {//如果目录里已有文件则先清空，仅局限于文件而无法删除子文件夹
            String[] items = target.list();
            for (String item : items) {
                File fullPath = new File(target, item);
                fullPath.delete();
            }
        }

        List<MultipartFile> files = multiRequest.getFiles("file");//这里似乎需要某部分的名字为file？
        try {
            for (MultipartFile file : files)
                file.transferTo(new File(target, file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
            return new BasicResponse(false, "上传失败");
        }
        return new BasicResponse(true, "");
    }

    @RequestMapping(value = "/attachment_check", method = RequestMethod.POST)
    public Map<String, Boolean> hasUploadedAttachment(@RequestBody String[] types, HttpServletRequest request, @Value("${savingPath}") String dest) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("hasUploaded", true);

        String email = getIdFromRequest(request);
        String studentName = service.getStudentName(email);
        File target = new File(dest + studentName + "-" + email);

        if (!target.exists()) {
            result.put("hasUploaded", false);
            return result;
        }
        for (String folder : types) {
            File fullPath = new File(target, folder);
            if (!fullPath.exists() || fullPath.list().length == 0) {
                result.put("hasUploaded", false);
                return result;
            }
        }
        return result;
    }

    @RequestMapping(value = "/attachment_names", method = RequestMethod.GET)
    public String[] getFileNames(HttpServletRequest request, @Value("${savingPath}") String dest) {
        String email = getIdFromRequest(request);
        String studentName = service.getStudentName(email);
        File target = new File(dest + studentName + "-" + email);
        if (!target.exists())
            return null;
        List<String> result = new ArrayList<>();
        String[] folders = target.list();
        for (String folder : folders)
            result.addAll(Arrays.asList(new File(target, folder).list()));
        String[] names = new String[result.size()];
        return result.toArray(names);
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
