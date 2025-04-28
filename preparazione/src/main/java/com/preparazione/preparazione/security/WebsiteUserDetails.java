package com.preparazione.preparazione.security;

import com.preparazione.preparazione.entities.WebsiteUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WebsiteUserDetails implements UserDetails {

    private final WebsiteUser websiteUser;

    public WebsiteUserDetails(WebsiteUser websiteUser) {
        this.websiteUser = websiteUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(websiteUser.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return websiteUser.getPassword();
    }

    @Override
    public String getUsername() {
        return websiteUser.getUser();
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
