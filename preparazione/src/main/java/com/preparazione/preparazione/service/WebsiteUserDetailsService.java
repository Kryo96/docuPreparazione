package com.preparazione.preparazione.service;

import com.preparazione.preparazione.entities.WebsiteUser;
import com.preparazione.preparazione.repo.WebsiteUserRepository;
import com.preparazione.preparazione.security.WebsiteUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WebsiteUserDetailsService implements UserDetailsService {

    private final WebsiteUserRepository userRepository;

    public WebsiteUserDetailsService(WebsiteUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WebsiteUser user = userRepository.findByUsername(username)
                .orElseThrow(
                        ()-> new UsernameNotFoundException("User not found"));
        return new WebsiteUserDetails(user);
    }
}
