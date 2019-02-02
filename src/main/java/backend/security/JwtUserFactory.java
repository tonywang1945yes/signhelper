package backend.security;

import backend.entity.Student;
import backend.enums.RoleName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public static JwtStduent create(Student student){
        return new JwtStduent(
                student.getName(),
                student.getPassword(),
                student.getEmail(),
                mapToGrantedAuthorities(Arrays.asList(RoleName.ROLE_STUDENT)),
                true,
                student.getLastLogOutDate(),
                student.getLastPasswordResetDate());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleName> roles) {
        return roles.stream()
                .map(roleName -> new SimpleGrantedAuthority(roleName.name()))
                .collect(Collectors.toList());
    }
}
