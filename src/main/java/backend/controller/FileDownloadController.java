package backend.controller;


import backend.parameter.downloadFile.DownloadParameter;
import backend.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/fileDownload")
public class FileDownloadController {

    @Autowired
    FileDownloadService service;


    @RequestMapping(value = "/fileStorage",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseBody
    public boolean downLoadFile(@RequestBody DownloadParameter parameter){
        service.saveUrlAs(parameter.getId(),parameter.getFilepath(),parameter.getMethod());
        return true;
    }
}
