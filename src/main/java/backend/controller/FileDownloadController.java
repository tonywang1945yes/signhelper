package backend.controller;


import backend.dao.service.ApplFormRepository;
import backend.entity.application.ApplForm;
import backend.parameter.downloadFile.DownloadParameter;
import backend.parameter.register.RegisterParameter;
import backend.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;


@RestController
@RequestMapping(value = "/fileDownload")
public class FileDownloadController {

    @Autowired
    FileDownloadService service;


//    @RequestMapping(value = "/fileStorage",
//            method = RequestMethod.GET,
//            consumes = {"application/json", "application/xml"},
//            produces = {"application/json", "application/xml"})
//    @ResponseBody
//    public boolean downLoadFile(@RequestBody DownloadParameter parameter){
//        service.saveUrlAs(parameter.getId(),parameter.getFilepath(),parameter.getMethod());
//        return true;
//    }




//    @RequestMapping(value = "/create")
//    public void create(){
//        service.createFile();
//    }

    @PostMapping(value = "/pdfCreation")
    public void create(@RequestBody DownloadParameter parameter){
        service.createApplicationPdf(parameter.getId());
    }
    @PostMapping(value = "/fileDownload")
    public void download(HttpServletResponse response) {
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + "Helloworld.pdf");
        InputStream in = null;
        try {
            in = new FileInputStream(new File("d:/Helloworld.pdf"));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                response.getOutputStream().write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
        }
    }
//
//    @RequestMapping(value = "/create")
//    public void create(){
//        service.createFile();
//    }
    @PostMapping(value = "/xlsxCreation")
    public void create(){
        service.createFile();
    }
}
