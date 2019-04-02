package backend.controller;

import backend.dao.service.ApplFormRepository;
import backend.entity.application.ApplForm;
import backend.entity.application.CustomResult;
import backend.enums.SubjectCriteria;
import backend.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static backend.enums.SubjectCriteria.*;
@RestController
@CrossOrigin
public class bugFixer {

    @Autowired
    ApplFormRepository applFormRepo;

    @GetMapping(value = "/fix")
    public BasicResponse fix(){
        List<ApplForm> list = applFormRepo.findAll();
        for (ApplForm application : list){
            int chinese = application.getActualLevelPoints().getChinese();
            int math = application.getActualLevelPoints().getMath();
            int english = application.getActualLevelPoints().getEnglish();
            int science = application.getActualLevelPoints().getSciences();
            int social = application.getActualLevelPoints().getSocials();
            CustomResult<SubjectCriteria> res = new CustomResult<>();
            res.setChinese(calculate(chinese, 13, 13, 11, 9, 8));
            res.setEnglish(calculate(english, 14, 13, 10, 5, 4));
            res.setMath(calculate(math, 14, 12, 9, 5, 4));
            res.setSocials(calculate(social, 13, 12, 10, 9, 7));
            res.setSciences(calculate(science, 13, 11, 8, 6, 5));
            application.setSingleSubjectCriteria(res);
            applFormRepo.save(application);
        }
        return new BasicResponse(true, "");
    }

    private SubjectCriteria calculate(int score, int first, int second, int third, int forth, int fifth){
        if (score >= first){
            return TOP_CRITERIA;
        }
        else if (score >= second){
            return HEAD_CRITERIA;
        }
        else if (score >= third){
            return AVERAGE_CRITERIA;
        }
        else if (score >= forth){
            return BACK_CRITERIA;
        }
        else {
            return BOTTOM_CRITERIA;
        }
    }
}
