package com.leasing.app.service;

import com.leasing.app.model.Client;
import com.leasing.app.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> getFastSearch(String term) {
        return clientRepository.fastSearchByClientValue(term);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

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

