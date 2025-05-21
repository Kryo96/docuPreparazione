package com.leasing.app.repository;

import com.leasing.app.model.common.contract.ProductContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductContractRepository extends JpaRepository<ProductContract, Long> {
}
