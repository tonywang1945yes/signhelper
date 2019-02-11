package backend.service;

import backend.dao.impl.HibernateDao;
import backend.entity.Student;
import backend.security.JwtUserFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HibernateDao<Student> dao = new HibernateDao<>(new Student());
        Student student = dao.findByKey(username);

        if (student == null) {
            throw new UsernameNotFoundException(String.format("No student found with email '%s'.", username));
            //说是这么说，但在代码中此异常被隐藏，包装为密码错误异常
        } else {
            return JwtUserFactory.create(student);
        }
    }
}