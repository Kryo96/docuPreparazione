package com.leasing.app.service;

import com.leasing.app.details.WebUserDetails;
import com.leasing.app.model.WebUser;
import com.leasing.app.repository.WebUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WebUserDetailsService implements UserDetailsService {
    private final WebUserRepository userRepository;

    public WebUserDetailsService(WebUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WebUser user = userRepository.findByUsername(username)
                .orElseThrow(
                        ()-> new UsernameNotFoundException("User not found"));
        return new WebUserDetails(user);
    }


}
