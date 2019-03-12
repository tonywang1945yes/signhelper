package backend.service;

import backend.dao.service.ApplFormRepository;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
import backend.entity.application.ApplForm;
import backend.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateStudentNameService {

    @Autowired
    ApplFormRepository applFormRepo;

    @Autowired
    StudentRepository stuRepo;
    public List<ApplForm> getApplFormList(){
        return applFormRepo.findByNeedSimplification(true);
    }
//
//    public Boolean update(List<ApplForm> list){
//
//        for (ApplForm applForm : list){
//            applFormRepo.save(applForm);
////            to check if the data has been stored normally
//            ApplForm new_applForm = applFormRepo.findByStudentId(applForm.getStudentId()).get(0);
//            if (!applForm.getFirstName().equals(new_applForm.getFirstName()) || !applForm.getLastName().equals(new_applForm.getLastName())){
//                return false;
//            }
//        }
//        return true;
//    }
    public BasicResponse update(String identity,String firstName,String lastName){
        BasicResponse response = new BasicResponse();
        ApplForm form = applFormRepo.findByIdentityNum(identity);
        if(form == null){
            response.setMsg("Fail to find ApplForm with identityNum: "+identity);
            response.setSucceed(false);
            return response;
        }
        else {
            form.setFirstName(firstName);
            form.setLastName(lastName);
            applFormRepo.save(form);

            response.setSucceed(true);
            response.setMsg("");
            return response;
        }
    }
}
