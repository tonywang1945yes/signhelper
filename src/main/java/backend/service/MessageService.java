package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.AssessmentResultRepository;
import backend.dao.service.MessageRepository;
import backend.entity.Administer;
import backend.entity.AssessmentResult;
import backend.entity.Message;
import backend.enums.StudentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepo;

    @Autowired
    AssessmentResultRepository assessmentResultRepo;

    public void updateState(String message, StudentState state) {//state只有4种： JUNIOR_PASSED, JUNIOR_FAILED, SENIOR_PASSED, SENIOR_FAILED
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
            FileWriter writer=new FileWriter(writeName);
            writer.write(message);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        AssessmentResult r = assessmentResultRepo.findByEmail(email);
        r.setAttendSecondTest(willing);
        assessmentResultRepo.save(r);
    }
}
