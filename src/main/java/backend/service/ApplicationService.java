package backend.service;

import backend.dao.service.*;
import backend.entity.Student;
import backend.entity.application.ApplForm;
import backend.enums.StudentState;
import backend.parameter.application.ApplFormParameter;
import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ApplicationService {

    @Autowired
    ApplFormRepository applFormRepo;

    @Autowired
    ActivityRepository activityRepo;

    @Autowired
    FamilyParticularRepository familyParticularRepo;

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    AdministerRepository administerRepo;

    @Transactional
    public boolean updateApplForm(ApplFormParameter param, String email) {
        Student student = studentRepo.findById(email).get();
        switch (param.getCacheOrSubmit()) {
            case 0:
                student.setStudentState(StudentState.FORM_CACHED);
                break;
            case 1:
                student.setStudentState(StudentState.UNDER_EXAMINED);
                break;
            default:
                return false;
        }
        studentRepo.save(student);

        ApplForm applForm = applFormRepo.findByStudentId(email).get(0);
        if (applForm.getActivities() != null) {
            activityRepo.deleteActivities(applForm.getId());
        }
        if (applForm.getFamilyParticulars() != null) {
            familyParticularRepo.deleteItems(applForm.getId());
        }
        ApplForm updated = applFormRepo.saveAndFlush(applForm);
        updated.updateInfo(param);
        applFormRepo.saveAndFlush(updated);
        return true;
    }

    public boolean sendAttachment(String dest, String email, String type, List<MultipartFile> files) {
        File target = new File(getRootPath(dest, email), type);
        if (!target.exists())
            target.mkdirs();
        else {//如果目录里已有文件则先清空，仅局限于文件而无法删除子文件夹
            String[] items = target.list();
            for (String item : items) {
                File fullPath = new File(target, item);
                fullPath.delete();
            }
        }
        try {
            for (MultipartFile file : files)
                file.transferTo(new File(target, file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
            for (String item : target.list())
                new File(target, item).delete();
            target.delete();
            return false;
        }
        return true;
    }

    public boolean hasUploadedAttachment(String dest, String email, String[] types) {
        File rootPath = getRootPath(dest, email);
        if (!rootPath.exists())
            return false;
        for (String folder : types) {
            File fullPath = new File(rootPath, folder);
            if (!fullPath.exists() || fullPath.list().length == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean hasUploadedApplForm(String email) {
        if (studentRepo.existsById(email)) {
            Student student = studentRepo.findById(email).get();
            return student.getStudentState().equals(StudentState.UNDER_EXAMINED);
        }
        return false;
    }

    public String[] getFileNames(String dest, String email) {
        File rootPath = getRootPath(dest, email);
        if (!rootPath.exists())
            return null;

        List<String> result = new ArrayList<>();
        String[] folders = rootPath.list();
        for (String folder : folders)
            result.addAll(Arrays.asList(new File(rootPath, folder).list()));
        String[] names = new String[result.size()];
        return result.toArray(names);
    }

    public ApplForm getApplicationForm(String studentId) {
        return applFormRepo.findByStudentId(studentId).get(0);
    }

    public String simplifyChars(String origin) {
        StringBuilder raw = new StringBuilder(origin);
        StringBuilder simplified = new StringBuilder();
        for (int i = 0; i < raw.length(); i++) {
            //因担心发生词语转换而拆开字符串分开转换，性能有待研究
            simplified.append(HanLP.convertToSimplifiedChinese(raw.substring(i, i + 1)));
        }
        return simplified.toString();
    }

    public boolean beforeDDL() {
        Calendar now = Calendar.getInstance();
        Calendar ddl = administerRepo.findAll().get(0).getDdl();
        return now.before(ddl);
    }

    private String getStudentName(String email) {
        return studentRepo.getOne(email).getName();
    }

    private File getRootPath(String dest, String email) {
        String studentName = getStudentName(email);
        return new File(dest + "/" + studentName + "-" + email);
    }


}
