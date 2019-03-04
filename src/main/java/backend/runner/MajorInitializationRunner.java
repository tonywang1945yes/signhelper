package backend.runner;

import backend.dao.service.MajorRepository;
import backend.entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MajorInitializationRunner implements ApplicationRunner{

    @Autowired
    MajorRepository majorRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        Major major = new Major("漢語言文學",true,4,"文學院","一流學科：中國語言文學");
        majorRepo.save(major);
        major = new Major("歷史學類",true,4,"歷史學院","");
        majorRepo.save(major);
        major = new Major("哲學",true,4,"哲學系","一流學科：哲學");
        majorRepo.save(major);
        major = new Major("新聞傳播學類",true,4,"新聞傳播學院","");
        majorRepo.save(major);
        major = new Major("法學",true,4,"法學院","");
        majorRepo.save(major);
        major = new Major("漢語國際教育",true,4,"海外教育學院","");
        majorRepo.save(major);
        major = new Major("英語",true,4,"外國語學院","一流學科：外國語言文學");
        majorRepo.save(major);
        major = new Major("俄語",true,4,"外國語學院","一流學科：外國語言文學");
        majorRepo.save(major);
        major = new Major("日語",true,4,"外國語學院","一流學科：外國語言文學");
        majorRepo.save(major);
        major = new Major("法語",true,4,"外國語學院","一流學科：外國語言文學");
        majorRepo.save(major);
        major = new Major("德語",true,4,"外國語學院","一流學科：外國語言文學");
        majorRepo.save(major);
        major = new Major("西班牙語",true,4,"外國語學院","一流學科：外國語言文學");
        majorRepo.save(major);
        major = new Major("朝鮮語",true,4,"外國語學院","一流學科：外國語言文學");
        majorRepo.save(major);
        major = new Major("社會學類",true,4,"社會學院","");
        majorRepo.save(major);
        major = new Major("公共管理類",true,4,"政府管理學院","");
        majorRepo.save(major);
        major = new Major("信息管理與信息系統",true,4,"信息管理學院","一流學科：讀書情報與檔案管理");
        majorRepo.save(major);
        major = new Major("經濟學類",false,4,"商學院","");
        majorRepo.save(major);
        major = new Major("工商管理類",false,4,"商學院","");
        majorRepo.save(major);
        major = new Major("數學類",false,4,"數學系","");
        majorRepo.save(major);
        major = new Major("物理學類",false,4,"物理學院","一流學科：物理學");
        majorRepo.save(major);
        major = new Major("天文學類",false,4,"天文與空間科學學院","一流學科：天文學");
        majorRepo.save(major);
        major = new Major("化學類",false,4,"化學化工學院","一流學科：化學、化學工程與技術");
        majorRepo.save(major);
        major = new Major("電子信息類",false,4,"電子科學與工程學院","");
        majorRepo.save(major);
        major = new Major("材料學",false,4,"現代工程與應用科學學院","一流學科：材料科學與工程");
        majorRepo.save(major);
        major = new Major("地質學類",false,4,"地球科學與工程學院","一流學科：地質學、礦業工程");
        majorRepo.save(major);
        major = new Major("大氣科學類",false,4,"大氣科學學院","一流學科：大氣科學");
        majorRepo.save(major);
        major = new Major("地理科學類",false,4,"地理與海洋科學學院","");
        majorRepo.save(major);
        major = new Major("環境科學與工程類",false,4,"環境學院","一流學科：環境科學與工程");
        majorRepo.save(major);
        major = new Major("生物科學類",false,4,"生命科學學院","一流學科：生物學");
        majorRepo.save(major);
        major = new Major("口腔醫學（五年）",false,5,"醫學院","");
        majorRepo.save(major);
        major = new Major("自動化",false,4,"工程管理學院","");
        majorRepo.save(major);
        major = new Major("工業工程類",false,4,"工程管理學院","");
        majorRepo.save(major);
        major = new Major("軟件工程",false,4,"軟件學院","計算機科學與技術");
        majorRepo.save(major);
        major = new Major("城鄉規劃",false,4,"建築與城市規劃學院","");
        majorRepo.save(major);
        major = new Major("建築學",false,4,"建築與城市規劃學院","");
        majorRepo.save(major);

    }


}
