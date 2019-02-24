package backend.security;

import backend.entity.User;
import backend.enums.RoleName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public static JwtUser create(User user){
        return new JwtUser(
                user.getName(),
                user.getPasswordEncoded(),
                mapToGrantedAuthorities(Arrays.asList(user.getRoleName())),
                true,
                user.getLastLogoutDate(),
                user.getLastPasswordResetDate());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleName> roles) {
        return roles.stream()
                .map(roleName -> new SimpleGrantedAuthority(roleName.name()))
                .collect(Collectors.toList());
    }
}
