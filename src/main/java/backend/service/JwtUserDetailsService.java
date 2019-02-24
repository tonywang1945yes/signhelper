package backend.service;

import backend.dao.service.UserRepository;
import backend.entity.User;
import backend.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getOne(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", username));
            //说是这么说，但在代码中此异常被隐藏，包装为密码错误异常
        } else {
            return JwtUserFactory.create(user);
        }
    }
}