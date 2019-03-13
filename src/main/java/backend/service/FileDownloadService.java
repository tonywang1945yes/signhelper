package backend.service;


import backend.dao.service.ApplFormRepository;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
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
    CreatePdfFile service;
    public BasicResponse createApplicationPdf(String[] ids){
        BasicResponse response = new BasicResponse();
        String photopath = docPath ;
        for(int i=0; i<ids.length;i++){
            String idnum = ids[i];
            ApplForm form = applFormRepo.findByIdentityNum(idnum);
            List<Student> stus = stuRepo.findByIdentityNum(idnum);
            if(form != null){
                photopath = photopath + "/"+stus.get(0).getName()+"-"+stuRepo.findByApplFormId(form.getId()).getEmail()+"/"+"考生照片";
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
    public void createFile(){
        List<ApplForm> list = applFormRepo.findAll();
        if(list.size()==0){
            return;
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生申请表信息");
        Row title = sheet.createRow(0);
        Row data;

        Cell title0 = title.createCell(0);
        title0.setCellValue("email");

        Cell title1 = title.createCell(1);
        title1.setCellValue("studentId");

//        combine first name and last name ?
        Cell title2 = title.createCell(2);
        title2.setCellValue("firstName");

        Cell title3 = title.createCell(3);
        title3.setCellValue("lastName");

        Cell title4 = title.createCell(4);
        title4.setCellValue("sex");

        Cell title5 = title.createCell(5);
        title5.setCellValue("birthDate");

        Cell title6 = title.createCell(6);
        title6.setCellValue("visaNum");

        Cell title7 = title.createCell(7);
        title7.setCellValue("identityNum");

        Cell title8 = title.createCell(8);
        title8.setCellValue("highSchool");

//        not a must ?
        Cell title9 = title.createCell(9);
        title9.setCellValue("graduationYear");

        Cell title10 = title.createCell(10);
        title10.setCellValue("address");

        Cell title11 = title.createCell(11);
        title11.setCellValue("postalCode");

        Cell title12 = title.createCell(12);
        title12.setCellValue("phoneNum");

        Cell title13 = title.createCell(13);
        title13.setCellValue("curriculumChoice");

        Cell title14 = title.createCell(14);
        title14.setCellValue("artOrSci");

        Cell title15 = title.createCell(15);
        title15.setCellValue("acceptAssignment");

        Cell title16 = title.createCell(16);
        title16.setCellValue("gsatResult");


        for (int i = 1; i <= list.size(); i ++){
            data = sheet.createRow(i);
            ApplForm applForm = list.get(i - 1);

            Cell data0 = data.createCell(0);
            data0.setCellValue(applForm.getId());

            Cell data1 = data.createCell(1);
            data1.setCellValue(applForm.getStudentId());

            Cell data2 = data.createCell(2);
            data2.setCellValue(applForm.getFirstName());

            Cell data3 = data.createCell(3);
            data3.setCellValue(applForm.getLastName());

            Cell data4 = data.createCell(4);
            data4.setCellValue(applForm.getSex());

            Cell data5 = data.createCell(5);
            data5.setCellValue(applForm.getBirthDate());

            Cell data6 = data.createCell(6);
            data6.setCellValue(applForm.getVisaNum());

            Cell data7 = data.createCell(7);
            data7.setCellValue(applForm.getIdentityNum());

            Cell data8 = data.createCell(8);
            data8.setCellValue(applForm.getHighSchool());

            Cell data9 = data.createCell(9);
            data9.setCellValue(applForm.getGraduationYear());

            Cell data10 = data.createCell(10);
            data10.setCellValue(applForm.getAddress());

            Cell data11 = data.createCell(11);
            data11.setCellValue(applForm.getPostalCode());

            Cell data12 = data.createCell(12);
            data12.setCellValue(applForm.getPhoneNumbers().toString());

            Cell data13 = data.createCell(13);
            data13.setCellValue(applForm.getCurriculumChoices().toString());

            Cell data14 = data.createCell(14);
            if (applForm.getArtOrSci() == 0){
                data14.setCellValue("art");
            }
            data14.setCellValue("sci");

            Cell data15 = data.createCell(15);
            data15.setCellValue(applForm.getAcceptAssignment());

            Cell data16 = data.createCell(16);
            data16.setCellValue(applForm.getResults().toString());
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
