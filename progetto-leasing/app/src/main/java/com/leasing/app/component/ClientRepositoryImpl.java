package com.leasing.app.component;

import com.leasing.app.model.Client;
import com.leasing.app.repository.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> leasingFastSearchByClient(String uniqueStringForIdentity, String leasingType) {
        String queryToSearch = "SELECT e FROM Client e WHERE e.uniqueStringForIdentity = :uniqueStringForIdentity";
        TypedQuery<Client> query = entityManager.createQuery(queryToSearch, Client.class);
        query.setParameter("uniqueStringForIdentity", uniqueStringForIdentity);
        return query.getResultList();
    }

}
