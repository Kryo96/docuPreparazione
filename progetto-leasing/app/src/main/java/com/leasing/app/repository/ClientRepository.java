package com.leasing.app.repository;

import com.leasing.app.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c " +
            "WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "OR LOWER(c.email) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "OR LOWER(c.uniqueStringForIdentity) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Client> fastSearchByClientValue (@Param("term") String term);
}
