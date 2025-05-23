package com.leasing.app.service;

import com.leasing.app.dto.ClientDTO;
import com.leasing.app.dto.signup.SignupDTO;
import com.leasing.app.model.Client;
import com.leasing.app.model.WebUser;
import com.leasing.app.model.common.cash.BankAccount;
import com.leasing.app.repository.BankAccountRepository;
import com.leasing.app.repository.ClientRepository;
import com.leasing.app.repository.WebUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {

    private final WebUserRepository webUserRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;

    public ClientService(WebUserRepository webUserRepository, BankAccountRepository bankAccountRepository, ClientRepository clientRepository) {
        this.webUserRepository = webUserRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> getFastSearch(String term) {
        return clientRepository.searchClients(term);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
    }

    public Client createClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName() != null ? clientDTO.getName():null);
        client.setEmail(clientDTO.getEmail()!= null?clientDTO.getEmail():null);
        client.setPhonenumber(clientDTO.getPhonenumber()!=null ? clientDTO.getPhonenumber() :null);
        client.setVatnumber(clientDTO.getVatnumber()!=null?clientDTO.getVatnumber():null);
        client.setUniqueStringForIdentity(String.valueOf(Timestamp.valueOf(LocalDateTime.now())));

        if(clientDTO.getWebUser() != null){
            WebUser webUser = webUserRepository.findById(clientDTO.getWebUser()).orElseThrow(() -> new EntityNotFoundException("WebUser not valid" + client.getWebUser()));
            client.setWebUser(webUser);
        }

        if(clientDTO.getBankAccount() != null){
            BankAccount bankAccount = bankAccountRepository.findById(clientDTO.getBankAccount()).orElseThrow(() -> new EntityNotFoundException("BankAccount not valid"));
            client.setBankAccount(bankAccount);
        }

        return clientRepository.save(client);
    }

    public void routineCreate(Long user_id, SignupDTO signupDTO){}

    public Client updateClient(Long id, Client updatedClient) {
        Client currentClient = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
        currentClient.setName(updatedClient.getName());
        currentClient.setEmail(updatedClient.getEmail());
        return clientRepository.save(currentClient);
    }

    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }
}

