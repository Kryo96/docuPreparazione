package com.leasing.app.repository.common;

import com.leasing.app.model.common.contract.LeasingContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeasingContractRepository extends JpaRepository<LeasingContract, Long> {
}
