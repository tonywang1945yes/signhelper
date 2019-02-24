package backend.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Calendar;
import java.util.Collection;

//Spring Security通过UserDetail的实现类来认证用户
//参考blog: https://www.jianshu.com/p/a65f883de0c1
public class JwtUser implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;
    private Calendar lastLogoutDate;
    private Calendar lastPasswordResetDate;

    public JwtUser(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            Calendar lastLogoutDate,
            Calendar lastPasswordResetDate) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastLogoutDate = lastLogoutDate;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Calendar getLastLogoutDate() {
        return lastLogoutDate;
    }

    public Calendar getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}