package backend.service;


import backend.dao.service.ApplFormRepository;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
import backend.enums.StudentState;
import backend.response.BasicResponse;
import backend.util.PdfUtil.CreatePdfFile;
import backend.util.zipUtil.setZip;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import backend.entity.application.ApplForm;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Service
public class FileDownloadService {

    /**
     * @功能 下载临时素材接口
     * @param filePath 文件将要保存的目录
     * @param method 请求方法，包括POST和GET
     * @param url 请求的路径
     * @return
     */
    @Autowired
    ApplFormRepository applFormRepo;

    @Autowired
    StudentRepository stuRepo;

    @Value("${downloadFileUrl}")
    String url;

//     服务器端文件临时存放路径
    @Value("${createFileUrl}")
    String filepath;

    @Value("${fileName}")
    String fileName;

    @Value("${savingPath}")
    String docPath;

    @Value("${savingPdfPath}")
    String zippath;

    @Autowired
    ApplicationService applicationService;
    @Autowired
    CreatePdfFile service;
    public BasicResponse createApplicationPdf(String[] ids){
        File file = new File(zippath);
        File[] content = file.listFiles();//取得当前目录下所有文件和文件夹
        if(content != null) {
            System.out.println(content.toString());
            if(content.length != 0 ) {
                for (File name : content) {
                    File temp = new File(zippath, name.getName());
                    if (temp.isDirectory()) {//判断是否是目录
                    } else {
                        if (!temp.delete()) {//直接删除文件
                            System.err.println("Failed to delete " + name);
                        }
                    }
                } //清空目录
            }
        }

        BasicResponse response = new BasicResponse();
        String photopath = docPath ;
        for(int i=0; i<ids.length;i++){
            String idnum = ids[i];
            ApplForm form = applFormRepo.findByIdentityNum(idnum);
            List<Student> stus = stuRepo.findByApplFormId(form.getId());
            if(form != null){
                photopath = photopath + "/"+stus.get(0).getName()+"-"+stus.get(0).getEmail()+"/"+"考生照片";
                try {
                File[] photo = new File(photopath).listFiles();
                service.create(form, photopath + "/" + photo[0].getName());
                }
                catch (ArrayIndexOutOfBoundsException e){
                    response.setMsg("No photo under path"+ photopath);
                    response.setSucceed(false);
                    return response;
                }catch (NullPointerException e ){
                    response.setMsg("No photo under path"+ photopath);
                    response.setSucceed(false);
                    return response;
                }catch (FileNotFoundException ex){
                    response.setMsg("Please select the right path to save pdf.");
                    response.setSucceed(false);
                    return response;
                }
                catch (IOException e){
                    response.setSucceed(false);
                    response.setMsg("字体生成玄学bug，请联系程序员进行处理");
                    return response;
                }
                catch (DocumentException e){
                    response.setMsg("Pdf生成错误");
                    response.setSucceed(false);
                    return response;
                }
                photopath = docPath;//路径还原
            }
            else{
                response.setMsg("No such id: "+idnum);
                response.setSucceed(false);
                return response;
            }
        }
        response.setSucceed(true);
        return response;
    }

    public BasicResponse createZip(){
        BasicResponse response = new BasicResponse();
        try {
            File isexisted= new File(zippath+"/"+"學生申請表.zip");
            if(isexisted.exists()){
                isexisted.delete();
            }
            response.setMsg(setZip.toZip(new File(zippath),"學生申請表"));
        }catch (FileNotFoundException e){
            response.setMsg(e.getMessage());
            response.setSucceed(false);
            return response;
        }catch (IOException ex){
            response.setMsg(ex.getMessage());
            response.setSucceed(false);
            return response;
        }catch (Exception exx){
            response.setMsg(exx.getMessage());
            response.setSucceed(false);
            return response;
        }
        response.setSucceed(true);
        return response;
    }

    public BasicResponse createAttachementZip(){
        BasicResponse response = new BasicResponse();
        try {
            File isexisted= new File(docPath+"/"+"學生附件.zip");
            if(isexisted.exists()){
                isexisted.delete();
            }
            response.setMsg(setZip.toZip(new File(docPath),"學生附件"));
        }catch (FileNotFoundException e){
            response.setMsg(e.getMessage());
            response.setSucceed(false);
            return response;
        }catch (IOException ex){
            response.setMsg(ex.getMessage());
            response.setSucceed(false);
            return response;
        }catch (Exception exx){
            response.setMsg(exx.getMessage());
            response.setSucceed(false);
            return response;
        }
        response.setSucceed(true);
        return response;
    }

    public void createFile(){
        String[] styles= new String[]{"考生照片","其他材料","身份证明","推荐信","学测成绩单"};
        List<ApplForm> list = applFormRepo.findAll();
        if(list.size()==0){
            return;
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生申请表信息");
        Row title = sheet.createRow(0);
        Row data;

        Cell title0 = title.createCell(0);
        title0.setCellValue("");

        Cell title1 = title.createCell(1);
        title1.setCellValue("email");

        Cell title2 = title.createCell(2);
        title2.setCellValue("name");

        Cell title4 = title.createCell(3);
        title4.setCellValue("sex");

        Cell title5 = title.createCell(4);
        title5.setCellValue("birthDate");

        Cell title6 = title.createCell(5);
        title6.setCellValue("visaNum");

        Cell title7 = title.createCell(6);
        title7.setCellValue("identityNum");

        Cell title8 = title.createCell(7);
        title8.setCellValue("highSchool");

        Cell title9 = title.createCell(8);
        title9.setCellValue("graduationYear");

        Cell title10 = title.createCell(9);
        title10.setCellValue("address");

        Cell title11 = title.createCell(10);
        title11.setCellValue("postalCode");

        Cell title12 = title.createCell(11);
        title12.setCellValue("phoneNum");

        Cell title13 = title.createCell(12);
        title13.setCellValue("curriculumChoice");

        Cell title14 = title.createCell(13);
        title14.setCellValue("artOrSci");

        Cell title15 = title.createCell(14);
        title15.setCellValue("acceptAssignment");

        Cell title16 = title.createCell(15);
        title16.setCellValue("成绩：语文/数学/英语/社会/自然");

        Cell title17 = title.createCell(16);
        title17.setCellValue("实得极分：语文/数学/英语/社会/自然");

        Cell title18 = title.createCell(17);
        title18.setCellValue("级距：语文/数学/英语/社会/自然");

        Cell title19 = title.createCell(18);
        title19.setCellValue("单科标准:语文/数学/英语/社会/自然");

        int rowIndex = 1;
        for (int i = 0; i < list.size(); i ++){
            ApplForm applForm = list.get(i);
            Student student = stuRepo.findByApplFormId(applForm.getId()).get(0);
            if (student.getStudentState() == StudentState.NULL || student.getStudentState() == StudentState.FORM_CACHED){
                continue;
            }
            if(!applicationService.hasUploadedAttachment(docPath,student.getEmail(),styles)){
                continue;
            }
            data = sheet.createRow(rowIndex ++);

            Cell data0 = data.createCell(0);
            data0.setCellValue("");

            Cell data1 = data.createCell(1);
            data1.setCellValue(applForm.getStudentId());

            Cell data2 = data.createCell(2);
            data2.setCellValue(applForm.getFirstName() + applForm.getLastName());

            Cell data4 = data.createCell(3);
            data4.setCellValue(applForm.getSex() == 0 ? "女" : "男");

            Cell data5 = data.createCell(4);
            Calendar date = applForm.getBirthDate();
            data5.setCellValue(date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.DATE));

            Cell data6 = data.createCell(5);
            data6.setCellValue(applForm.getVisaNum());

            Cell data7 = data.createCell(6);
            data7.setCellValue(applForm.getIdentityNum());

            Cell data8 = data.createCell(7);
            data8.setCellValue(applForm.getHighSchool());

            Cell data9 = data.createCell(8);
            data9.setCellValue(applForm.getGraduationYear());

            Cell data10 = data.createCell(9);
            data10.setCellValue(applForm.getAddress());

            Cell data11 = data.createCell(10);
            data11.setCellValue(applForm.getPostalCode());

            Cell data12 = data.createCell(11);
            data12.setCellValue(applForm.getPhoneNumbers().toString());

            Cell data13 = data.createCell(12);
            data13.setCellValue(applForm.getCurriculumChoices().toString());

            Cell data14 = data.createCell(13);
            if (applForm.getArtOrSci() == 0){
                data14.setCellValue("art");
            }else {
                data14.setCellValue("sci");
            }
//            data14.setCellValue(applForm.getArtOrSci());

            Cell data15 = data.createCell(14);
            data15.setCellValue(applForm.getAcceptAssignment() ? "是" : "否");

            Cell data16 = data.createCell(15);
            data16.setCellValue(applForm.getResults() == null ? "" : applForm.getResults().toString());

            Cell data17 = data.createCell(16);
            data17.setCellValue(applForm.getActualLevelPoints().toString());

            Cell data18 = data.createCell(17);
            data18.setCellValue(applForm.getLevelRange().toString());

            Cell data19 = data.createCell(18);
            data19.setCellValue(applForm.getSingleSubjectCriteria().toString());
        }

        FileOutputStream out = null;
        try {
            File file = new File(filepath);
            if (!file.exists()){
                file.mkdirs();
            }
            out = new FileOutputStream(filepath+"/"+fileName);
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


//    public static void main(String args[]){
//        System.out.println(Calendar.getInstance());
//    }

}
