package com.preparazione.preparazione.controller;

import com.preparazione.preparazione.entities.WebsiteUser;
import com.preparazione.preparazione.repo.WebsiteUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final WebsiteUserRepository websiteUser;

    public UserController(WebsiteUserRepository websiteUser) {
        this.websiteUser = websiteUser;
    }

    @GetMapping("/users")
    List<WebsiteUser> getUsers(){
        return websiteUser.findAll();
    }

    @GetMapping("/userById/{id}")
    WebsiteUser getUserById(@PathVariable Long id) throws Exception {
        return websiteUser.findById(id).orElseThrow(Exception::new);
    }

    @PostMapping("/users")
    WebsiteUser newWebsiteUser(@RequestBody WebsiteUser user){
        return websiteUser.save(user);
    }

    @PutMapping("/users/{id}")
    WebsiteUser replaceUser(@RequestBody WebsiteUser newUser, @PathVariable Long id){
        return websiteUser.findById(id).map( user -> {
                    user.setEmail(newUser.getEmail());
                    user.setName(newUser.getName());
                    return websiteUser.save(user);
                })
                .orElseGet(() -> {
                    return websiteUser.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id){
        websiteUser.deleteById(id);
    }
}
