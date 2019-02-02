package backend.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtStduent implements UserDetails {

    private String username;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;
    private Date lastLogOutDate;
    private Date lastPasswordResetDate;

    public JwtStduent(
            String username,
            String password,
            String email,
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            Date lastLogOutDate,
            Date lastPasswordResetDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastLogOutDate = lastLogOutDate;
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

    public String getEmail() {
        return email;
    }

    public Date getLastLogOutDate() {
        return lastLogOutDate;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}