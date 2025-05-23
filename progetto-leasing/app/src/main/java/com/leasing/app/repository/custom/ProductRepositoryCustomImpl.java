package com.leasing.app.repository.custom;

import com.leasing.app.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> fastSearchByProductValue(String name, String model, String organization, String agency) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        Predicate modelPredicate = cb.like(cb.lower(root.get("model")), "%" + model.toLowerCase() + "%");
        Predicate organizationPredicate = cb.like(cb.lower(root.get("organization")), "%" + organization.toLowerCase() + "%");
        Predicate agencyPredicate = cb.like(cb.lower(root.get("agency")), "%" + agency.toLowerCase() + "%");

        query.where(cb.or(namePredicate,modelPredicate,organizationPredicate,agencyPredicate));
        return entityManager.createQuery(query).getResultList();

    }
}
