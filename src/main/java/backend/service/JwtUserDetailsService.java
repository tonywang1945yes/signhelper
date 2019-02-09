package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.StudentRepository;
import backend.entity.Student;
import backend.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HibernateDao<Student> dao = new HibernateDao<>(new Student());
        String sql = "SELECT * FROM tbl_student WHERE name = "+username;
        Student student = dao.executeQuerySql(sql).get(0);

        if (student == null) {
            throw new UsernameNotFoundException(String.format("No student found with email '%s'.", username));
        } else {
            return JwtUserFactory.create(student);
        }
    }
}