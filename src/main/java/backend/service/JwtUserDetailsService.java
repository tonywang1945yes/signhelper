package backend.service;

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
        Student student = studentRepository.findByName(username);

        if (student == null) {
            throw new UsernameNotFoundException(String.format("No student found with email '%s'.", username));
        } else {
            return JwtUserFactory.create(student);
        }
    }
}