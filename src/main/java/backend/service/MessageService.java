package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.AdministerRepository;
import backend.dao.service.MessageRepository;
import backend.dao.service.StudentRepository;
import backend.entity.Administer;
import backend.entity.Message;
import backend.entity.Student;
import backend.enums.AdministerState;
import backend.enums.MessageType;
import backend.enums.StudentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static backend.enums.StudentState.*;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepo;

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    AdministerRepository administerRepo;

    @Value("${createFileUrl}")
    String messageTemplatePath;

    static final String JUNIOR_FAILED_PATH = "/junior_failed.txt";
    static final String JUNIOR_PASSED_PATH = "/junior_passed.txt";
    static final String SENIOR_FAILED_PATH = "/senior_failed.txt";
    static final String SENIOR_PASSED_PATH = "/senior_passed.txt";

    public boolean updateTemplate(String content, StudentState state) {//state只有4种： JUNIOR_PASSED, JUNIOR_FAILED, SENIOR_PASSED, SENIOR_FAILED
        String path = messageTemplatePath;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        switch (state) {
            case NULL:
                return false;
            case UNDER_EXAMINED:
                return false;
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
            default:
                return false;
        }
        try {
            File writeName = new File(path);
            writeName.createNewFile();
            FileWriter writer = new FileWriter(writeName);
            writer.write(content);
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getTemplate(StudentState state) {
        if (state == null)
            return null;
        File file = null;
        String line;
        StringBuilder res = new StringBuilder();
        if (Arrays.asList(JUNIOR_FAILED, JUNIOR_PASSED, SENIOR_FAILED).contains(state)) {
            file = new File(messageTemplatePath + "/" + state.toString().toLowerCase() + ".txt");
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                res.append("\n" + line);
            }
            return res.substring(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendSingleResultMessage(String studentId, StudentState studentState, AdministerState administerState) {
        Message message = new Message();
        message.setEmail(studentId);
        message.setReleasedTime(Calendar.getInstance());
        message.setTitle(administerState == AdministerState.JUNIOR_CHECKING ?
                "第一次审核结果" : "第二次审核结果");
        message.setType(administerState == AdministerState.JUNIOR_CHECKING ?
                MessageType.FIRST_ASSESSMENT : MessageType.SECOND_ASSESSMENT);
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

    public void sendResultMessage() {
        Administer administer = administerRepo.findAll().get(0);
        List<Student> list = studentRepo.findAllByStudentState(JUNIOR_PASSED);
        for (Student student : list) {
            sendSingleResultMessage(student.getEmail(), JUNIOR_PASSED, administer.getState());
        }
        list = studentRepo.findAllByStudentState(JUNIOR_FAILED);
        for (Student student : list) {
            sendSingleResultMessage(student.getEmail(), JUNIOR_FAILED, administer.getState());
        }
        list = studentRepo.findAllByStudentState(StudentState.SENIOR_PASSED);
        for (Student student : list) {
            sendSingleResultMessage(student.getEmail(), StudentState.SENIOR_PASSED, administer.getState());
        }
        list = studentRepo.findAllByStudentState(SENIOR_FAILED);
        for (Student student : list) {
            sendSingleResultMessage(student.getEmail(), SENIOR_FAILED, administer.getState());
        }
    }

    //建议使用此方法，每个状态开启一次流
    public void sendResultMessages(List<String> studentIds, StudentState studentState, AdministerState administerState) {
        String content = null;
        switch (studentState) {//暂时相信学生和管理员的状态是配套的
            case JUNIOR_FAILED:
                content = getTemplateContent(messageTemplatePath + JUNIOR_FAILED_PATH);
                break;
            case JUNIOR_PASSED:
                content = getTemplateContent(messageTemplatePath + JUNIOR_PASSED_PATH);
                break;
            case SENIOR_FAILED:
                content = getTemplateContent(messageTemplatePath + SENIOR_FAILED_PATH);
                break;
            case SENIOR_PASSED:
                content = getTemplateContent(messageTemplatePath + SENIOR_PASSED_PATH);
                break;
        }

        MessageType type = administerState == AdministerState.JUNIOR_CHECKING ?
                MessageType.FIRST_ASSESSMENT : MessageType.SECOND_ASSESSMENT;
        String title = administerState == AdministerState.JUNIOR_CHECKING ?
                "第一次审核结果" : "第二次审核结果";

        List<Message> messages = new ArrayList<>();
        for (String id : studentIds) {
            Message message = new Message();
            message.setEmail(id);
            message.setTitle(title);
            message.setType(type);
            message.setContent(content);
            message.setReleasedTime(Calendar.getInstance());
            messages.add(message);
        }

        messageRepo.saveAll(messages);
    }

    //    检查是否所有学生申请表均已被审核
    public Boolean check() {
        List<Student> list = studentRepo.findAllByStudentState(StudentState.UNDER_EXAMINED);
        if (list.size() == 0) {
            return true;
        }
        return false;
    }

    public boolean sendGlobalMessage(String title, String content) {
        List<Student> list = studentRepo.findAll();//假设学生表里的学生都是有效的，(也许需要每年删一次学生)
        List<String> emails = list.stream().map(Student::getEmail).collect(Collectors.toList());
        List<Message> toSave = new ArrayList<>();
        for (String email : emails)
            toSave.add(new Message(email, title, content, MessageType.NORMAL));
        messageRepo.saveAll(toSave);
        return true;
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

    public Message[] getMessageArray(String email) {
        List<Message> messages = messageRepo.findAllByEmail(email);
        Message[] res = new Message[messages.size()];
        return messages.toArray(res);
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
        if (willing)
            return;
        Student student = studentRepo.findById(email).get();
        student.setStudentState(StudentState.REJECT_ATTENDANCE);
        studentRepo.save(student);
    }
}
