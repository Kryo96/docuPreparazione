package com.leasing.app.repository;

import com.leasing.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(p.model) LIKE LOWER(CONCAT('%', :model, '%')) " +
            "OR LOWER(p.organization) LIKE LOWER(CONCAT('%', :organization, '%'))"+
            "OR LOWER(p.agency) LIKE LOWER(CONCAT('%', :agency, '%')) ")
    List<Product> fastSearchByProductValue (
            @Param("name") String name,
            @Param("model") String model,
            @Param("organization") String organization,
            @Param("agency") String agency
    );
}