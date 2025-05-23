package com.leasing.app.service;


import com.leasing.app.dto.ClientDTO;
import com.leasing.app.dto.signup.SignupDTO;
import com.leasing.app.model.Role;
import com.leasing.app.model.WebUser;
import com.leasing.app.repository.ClientRepository;
import com.leasing.app.repository.WebUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class WebUserService {
    private final PasswordEncoder passwordEncoder;
    private final WebUserRepository webUserRepository;
    private final ClientRepository clientRepository;
    private final ClientService clientService;

    public WebUserService(PasswordEncoder passwordEncoder, WebUserRepository webUserRepository, ClientRepository clientRepository, ClientService clientService) {
        this.passwordEncoder = passwordEncoder;
        this.webUserRepository = webUserRepository;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    public WebUser createWebUser(SignupDTO signupDTO){
        WebUser websiteUser = new WebUser();
        websiteUser.setName(signupDTO.getName());
        websiteUser.setEmail(signupDTO.getEmail());
        websiteUser.setUsername(signupDTO.getUsername());
        websiteUser.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        websiteUser.setRole(Role.USER);
        WebUser uid = webUserRepository.save(websiteUser);

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setEmail(signupDTO.getEmail());
        clientDTO.setName(signupDTO.getName());
        clientDTO.setVatnumber(signupDTO.getVatnumber());
        clientDTO.setPhonenumber(signupDTO.getPhonenumber());
        clientDTO.setWebUser(uid.getId());

        clientService.createClient(clientDTO);

        return uid;
    }
}
