package com.leasing.app.repository.common;

import com.leasing.app.model.common.CarLeasingContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarLeasingContractRepository extends JpaRepository<CarLeasingContract, Long> {
}

