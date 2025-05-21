package com.leasing.app.repository;

import com.leasing.app.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> leasingFastSearchByClient(String uniqueStringForIdentity, String leasingType);
}
