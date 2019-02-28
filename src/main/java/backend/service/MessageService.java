package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.AssessmentResultRepository;
import backend.dao.service.MessageRepository;
import backend.entity.Administer;
import backend.entity.AssessmentResult;
import backend.entity.Message;
import backend.entity.Student;
import backend.enums.AdministerState;
import backend.enums.StudentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepo;

    @Autowired
    AssessmentResultRepository assessmentResultRepo;

    @Value("${messageTemplatePath}")
    String messageTemplatePath;

    static final String JUNIOR_FAILED_PATH = "/junior_failed.txt";
    static final String JUNIOR_PASSED_PATH = "/junior_passed.txt";
    static final String SENIOR_FAILED_PATH = "/senior_failed.txt";
    static final String SENIOR_PASSED_PATH = "/senior_passed.txt";

    public void updateTemplate(String content, StudentState state) {//state只有4种： JUNIOR_PASSED, JUNIOR_FAILED, SENIOR_PASSED, SENIOR_FAILED
        String path = "src/main/resources/msgDic";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        switch (state) {
            case NULL:
                return;
            case UNDER_EXAMINED:
                return;
            case JUNIOR_FAILED:
                path += "/junior_failed.txt";
                break;
            case JUNIOR_PASSED:
                path += "/junior_passed.txt";
                break;
            case SENIOR_FAILED:
                path += "/senior_failed.txt";
                break;
            case SENIOR_PASSED:
                path += "/senior_passed.txt";
                break;
        }
        try {
            File writeName = new File(path);
            writeName.createNewFile();
            FileWriter writer = new FileWriter(writeName);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String studentId, StudentState studentState, AdministerState administerState) {
        Message message = new Message();
        message.setEmail(studentId);
        message.setReleasedTime(Calendar.getInstance());
        message.setTitle(administerState == AdministerState.JUNIOR_CHECKING ?
                "第一次审核结果" : "第二次审核结果");
        switch (studentState) {//暂时相信学生和管理员的状态是配套的
            case JUNIOR_FAILED:
                message.setContent(getTemplateContent(messageTemplatePath + JUNIOR_FAILED_PATH));
                break;
            case JUNIOR_PASSED:
                message.setContent(getTemplateContent(messageTemplatePath + JUNIOR_PASSED_PATH));
                break;
            case SENIOR_FAILED:
                message.setContent(getTemplateContent(messageTemplatePath + SENIOR_FAILED_PATH));
                break;
            case SENIOR_PASSED:
                message.setContent(getTemplateContent(messageTemplatePath + SENIOR_PASSED_PATH));
                break;
        }
        messageRepo.save(message);
    }

    public String getTemplateContent(String path) {
        ClassPathResource resource = new ClassPathResource(path);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            while ((line = reader.readLine()) != null) {
                sb.append(line + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

    public String getMessage() {
        HibernateDao<Administer> dao = new HibernateDao<>(new Administer());
        Administer administer = dao.getAllObjects().get(0);
        return administer.getMessage();
    }

    public Message[] getMessageList(String email) {
        return (Message[]) messageRepo.findAllByEmail(email).toArray();//排序晚点再做
    }

    public Message getMessageDetail(Long id, String email) {
        Message message = messageRepo.getOne(id);
        if (message.getEmail() != email)
            return null;
        return message;
    }

    public boolean updateMessagesState(Message[] messages) {
        List<Message> toSave = Arrays.asList(messages);
        messageRepo.saveAll(toSave);
        return true;
    }

    public void confirmSecondTestAttendance(String email, boolean willing) {
        AssessmentResult r = assessmentResultRepo.findByEmail(email).get(0);
        r.setAttendSecondTest(willing);
        assessmentResultRepo.save(r);
    }
}
