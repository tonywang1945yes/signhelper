package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import backend.enums.StudentState;
import org.springframework.stereotype.Service;

@Service
public class UpdateStudentService {
    public boolean updateState(String id, StudentState state){
        HibernateDao<Student> dao = new HibernateDao<>(new Student());
        Student student = dao.findByKey(id);
        student.setStudentState(state);
        dao.update(student);//dao.update()方法返回DatabaseRM枚举类的结果，不需要用下面的判断方式

        Student new_student = dao.findByKey(id);
        return new_student.getStudentState() == state;
    }
}
