package com.leasing.app.repository.common;

import com.leasing.app.model.common.CarLeasingContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateLeasingContract extends JpaRepository<CarLeasingContract, Long> {
}
