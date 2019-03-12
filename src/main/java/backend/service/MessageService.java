package backend.service;

import backend.dao.service.AdministerRepository;
import backend.dao.service.BroadcastRepository;
import backend.dao.service.ResultMessageRepository;
import backend.dao.service.StudentRepository;
import backend.entity.Administer;
import backend.entity.Student;
import backend.entity.message.Broadcast;
import backend.entity.message.Message;
import backend.entity.message.ResultMessage;
import backend.enums.AdministerState;
import backend.enums.ResultType;
import backend.enums.StudentState;
import backend.response.BasicResponse;
import backend.response.message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import static backend.enums.StudentState.*;

@Service
public class MessageService {

    @Autowired
    ResultMessageRepository resultMessageRepo;

    @Autowired
    BroadcastRepository broadcastRepo;

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

    public BasicResponse updateTemplate(String content, StudentState state) {//state只有4种： JUNIOR_PASSED, JUNIOR_FAILED, SENIOR_PASSED, SENIOR_FAILED
        BasicResponse response = new BasicResponse();
        String path = messageTemplatePath;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        switch (state) {
            case JUNIOR_FAILED:
            case JUNIOR_PASSED:
            case SENIOR_FAILED:
            case SENIOR_PASSED:
                path += ("/" + state.toString().toLowerCase() + ".txt");
                break;
            default:
                response.setSucceed(false);
                response.setMsg("No such state");
                return response;
        }
        try {
            File writeName = new File(path);
            writeName.createNewFile();
            FileWriter writer = new FileWriter(writeName);
            writer.write(content);
            writer.flush();
            writer.close();
            response.setSucceed(true);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setSucceed(false);
        response.setMsg("IOException");
        return response;
    }

    public MessageResponse getMessage(String email){
        MessageResponse mess =new MessageResponse();
        Student student = studentRepo.findByEmail(email);
        if(student!=null){
            int from ;
            switch (student.getStudentState()){
                case JUNIOR_PASSED:from=0;break;
                case JUNIOR_FAILED:from=1;break;
                case SENIOR_PASSED:from=3;break;
                case SENIOR_FAILED:from=4;break;
                default:from=-1;
                mess.setSucc(false);
                mess.setMessage("No such from");
                return mess;
            }
            mess.setFrom(from);
            String message = getTemplate(student.getStudentState());
            mess.setMessage(message);
            mess.setSucc(true);
            return mess;
        }else{
            mess.setMessage("No such id");
            mess.setSucc(false);
            return mess;
        }
    }
    public String getTemplate(StudentState state) {
        if (state == null)
            return null;
        String path;
        StringBuilder res = new StringBuilder();
        switch (state) {
            case JUNIOR_FAILED:
            case JUNIOR_PASSED:
            case SENIOR_FAILED:
            case SENIOR_PASSED:
                path = messageTemplatePath + "/" + state.toString().toLowerCase() + ".txt";
                break;
            default:
                return null;
        }
        try {
            Files.lines(Paths.get(path)).forEach(o -> res.append("\n").append(o));
            return res.substring(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAdminState(){
        return administerRepo.findAll().get(0).getMessage();
    }

    public void sendSingleResultMessage(String studentId, StudentState studentState, AdministerState administerState) {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setEmail(studentId);
        resultMessage.setReleasedTime(Calendar.getInstance());
        resultMessage.setTitle(administerState == AdministerState.JUNIOR_CHECKING ?
                "第一次审核结果" : "第二次审核结果");
        resultMessage.setType(administerState == AdministerState.JUNIOR_CHECKING ?
                ResultType.FIRST_ASSESSMENT : ResultType.SECOND_ASSESSMENT);
        switch (studentState) {//暂时相信学生和管理员的状态是配套的
            case JUNIOR_FAILED:
            case JUNIOR_PASSED:
            case SENIOR_FAILED:
            case SENIOR_PASSED:
                resultMessage.setContent(getTemplateContent(messageTemplatePath + "/" + studentState.toString().toLowerCase() + ".txt"));
                break;
            default:
                return;
        }
        resultMessageRepo.save(resultMessage);
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

        ResultType type = administerState == AdministerState.JUNIOR_CHECKING ?
                ResultType.FIRST_ASSESSMENT : ResultType.SECOND_ASSESSMENT;
        String title = administerState == AdministerState.JUNIOR_CHECKING ?
                "第一次审核结果" : "第二次审核结果";

        List<ResultMessage> resultMessages = new ArrayList<>();
        for (String id : studentIds) {
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setEmail(id);
            resultMessage.setTitle(title);
            resultMessage.setType(type);
            resultMessage.setContent(content);
            resultMessage.setReleasedTime(Calendar.getInstance());
            resultMessages.add(resultMessage);
        }

        resultMessageRepo.saveAll(resultMessages);
    }

    //    检查是否所有学生申请表均已被审核
    public Boolean check() {
        List<Student> list = studentRepo.findAllByStudentState(StudentState.UNDER_EXAMINED);
        if (list.size() == 0) {
            return true;
        }
        return false;
    }

    public List<Broadcast> getReleasedBroadcast() {
        return broadcastRepo.findAll();
    }

    @Transactional
    public boolean sendGlobalBroadcast(String title, String content) {
        broadcastRepo.save(new Broadcast(title, content, Calendar.getInstance()));
        return true;
    }

    @Transactional
    public boolean deleteBroadcast(List<Long> ids) {
        for (Long id : ids)
            broadcastRepo.deleteById(id);
        return true;
    }

    @Transactional
    public boolean updateBroadcast(List<Broadcast> broadcasts) {
        broadcastRepo.saveAll(broadcasts);
        return true;
    }

    public List<Message> getMessageList(String email) {
        List<Message> messages = new ArrayList<>();
        messages.addAll(resultMessageRepo.findAllByEmail(email));
        messages.addAll(broadcastRepo.findAll());
        return messages;
    }

    @Transactional
    public void confirmSecondTestAttendance(String email, boolean willing) {
        if (willing)
            return;
        studentRepo.findById(email).ifPresent(o -> {
            o.setStudentState(StudentState.REJECT_ATTENDANCE);
            studentRepo.save(o);
        });
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    private String getTemplateContent(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            Files.lines(Paths.get(path)).forEach(o -> builder.append("\n").append(o));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return builder.substring(1);
    }
}
