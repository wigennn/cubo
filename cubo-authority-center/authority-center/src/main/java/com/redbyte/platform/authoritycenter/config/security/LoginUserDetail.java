package com.redbyte.platform.authoritycenter.config.security;

import com.redbyte.platform.authoritycenter.core.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@Data
public class LoginUserDetail implements UserDetails {

    private static final long serialVersionUID = -5393953160563006455L;

    private List<SimpleGrantedAuthority> authorities;
    private String password;
    private String userName;

    private String token;
    private User user;
    private Long expireTime;


    LoginUserDetail(List<SimpleGrantedAuthority> authorities, String password, String userName, User user) {
        this.authorities = authorities;
        this.password = password;
        this.userName = userName;
        this.user = user;
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
        return userName;
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
        return true;
    }
}
