package com.leasing.app.repository;

import com.leasing.app.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT s FROM Client s WHERE s.uniqueStringForIdentity = :uniqueStringForIdentity")
    List<Client> leasingFastSearchByClient (@Param("uniqueStringForIdentity") String uniqueStringForIdentity);
}
