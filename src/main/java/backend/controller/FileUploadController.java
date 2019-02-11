package backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


@RestController
public class FileUploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void handleFilesUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
//        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for(MultipartFile file: files){
            if(!file.isEmpty()){
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;

                }
            }
        }
    }

}
