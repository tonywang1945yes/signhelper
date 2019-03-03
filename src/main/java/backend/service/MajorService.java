package backend.service;


import backend.dao.service.MajorRepository;
import backend.entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MajorService {

    @Autowired
    MajorRepository majorRepo;

    public void add(String name, Integer stuNum, Boolean acceptArt, Integer time, String college, Integer price, String comment){
        majorRepo.save(new Major(name, stuNum, acceptArt, time, college, price, comment));
    }

    public List<Major> getMajors(){
        return majorRepo.findAll();
    }

    public boolean init(){
        Major major = new Major("汉语言文学",true,4,"文学院","一流学科：中国语言文学");
        majorRepo.save(major);
        major = new Major("历史学类",true,4,"历史学院","");
        majorRepo.save(major);
        major = new Major("哲学",true,4,"哲学系","一流学科：哲学");
        majorRepo.save(major);
        major = new Major("新闻传播学类",true,4,"新闻传播学院","");
        majorRepo.save(major);
        major = new Major("法学",true,4,"法学院","");
        majorRepo.save(major);
        major = new Major("汉语国际教育",true,4,"海外教育学院","");
        majorRepo.save(major);
        major = new Major("英语",true,4,"外国语学院","一流学科：外国语言文学");
        majorRepo.save(major);
        major = new Major("俄语",true,4,"外国语学院","一流学科：外国语言文学");
        majorRepo.save(major);
        major = new Major("日语",true,4,"外国语学院","一流学科：外国语言文学");
        majorRepo.save(major);
        major = new Major("法语",true,4,"外国语学院","一流学科：外国语言文学");
        majorRepo.save(major);
        major = new Major("德语",true,4,"外国语学院","一流学科：外国语言文学");
        majorRepo.save(major);
        major = new Major("西班牙语",true,4,"外国语学院","一流学科：外国语言文学");
        majorRepo.save(major);
        major = new Major("朝鲜语",true,4,"外国语学院","一流学科：外国语言文学");
        majorRepo.save(major);
        major = new Major("社会学类",true,4,"社会学院","");
        majorRepo.save(major);
        major = new Major("公共管理类",true,4,"政府管理学院","");
        majorRepo.save(major);
        major = new Major("信息管理与信息系统",true,4,"信息管理学院","一流学科：图书情报与档案管理");
        majorRepo.save(major);
        major = new Major("经济学类",false,4,"商学院","");
        majorRepo.save(major);
        major = new Major("工商管理类",false,4,"商学院","");
        majorRepo.save(major);
        major = new Major("数学类",false,4,"数学系","");
        majorRepo.save(major);
        major = new Major("物理学类",false,4,"物理学院","一流学科：物理学");
        majorRepo.save(major);
        major = new Major("天文学类",false,4,"天文与空间科学学院","一流学科：天文学");
        majorRepo.save(major);
        major = new Major("化学类",false,4,"化学化工学院","一流学科：化学、化学工程与技术");
        majorRepo.save(major);
        major = new Major("电子信息类",false,4,"电子科学与工程学院","");
        majorRepo.save(major);
        major = new Major("材料类",false,4,"现代工程与应用科学学院","一流学科：材料科学与工程");
        majorRepo.save(major);
        major = new Major("地质学类",false,4,"地球科学与工程学院","一流学科：地质学、矿业工程");
        majorRepo.save(major);
        major = new Major("大气科学类",false,4,"大气科学学院","一流学科：大气科学");
        majorRepo.save(major);
        major = new Major("地理科学类",false,4,"地理与海洋科学学院","");
        majorRepo.save(major);
        major = new Major("环境科学与工程类",false,4,"环境学院","一流学科：环境科学与工程");
        majorRepo.save(major);
        major = new Major("生物科学类",false,4,"生命科学学院","一流学科：生物学");
        majorRepo.save(major);
        major = new Major("口腔医学（五年）",false,5,"医学院","");
        majorRepo.save(major);
        major = new Major("自动化",false,4,"工程管理学院","");
        majorRepo.save(major);
        major = new Major("工业工程类",false,4,"工程管理学院","");
        majorRepo.save(major);
        major = new Major("软件工程",false,4,"软件学院","计算机科学与技术");
        majorRepo.save(major);
        major = new Major("城乡规划",false,4,"建筑与城市规划学院","");
        majorRepo.save(major);
        major = new Major("建筑学",false,4,"建筑与城市规划学院","");
        majorRepo.save(major);

        return true;
    }
}
