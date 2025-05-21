package com.leasing.app.repository.custom;

import com.leasing.app.model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepositoryCustomImpl implements ClientRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> searchClients(String term) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = cb.createQuery(Client.class);
        Root<Client> root = query.from(Client.class);

        String liketerm = "%" + term.toLowerCase()+ "%";

        Predicate namePredicate = cb.like(cb.lower(root.get("name")), liketerm);
        Predicate emailPredicate = cb.like(cb.lower(root.get("email")), liketerm);
        Predicate identityPredicate = cb.like(cb.lower(root.get("uniqueStringForIdentity")), liketerm);

        query.where(cb.or(namePredicate, emailPredicate, identityPredicate));
        return entityManager.createQuery(query).getResultList();
    }
}
