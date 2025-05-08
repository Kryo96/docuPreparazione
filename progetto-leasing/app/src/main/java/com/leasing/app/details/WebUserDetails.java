package com.leasing.app.details;

import com.leasing.app.model.WebUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class WebUserDetails implements UserDetails {

    private final WebUser webUser;

    public WebUserDetails(WebUser webUser){
        this.webUser = webUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(webUser.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return webUser.getPassword();
    }

    @Override
    public String getUsername() {
        return webUser.getUsername();
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
