package com.leasing.app.service.common;

import com.leasing.app.model.common.CarLeasingContract;
import com.leasing.app.repository.common.CarLeasingContractRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarLeasingContractService {

    @Autowired
    private CarLeasingContractRepository repository;

    public CarLeasingContract save(CarLeasingContract carLeasingContract) {
        return repository.save(carLeasingContract);
    }

    public CarLeasingContract findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car Leasing Contract not found"));
    }

    public CarLeasingContract update(CarLeasingContract carLeasingContract) {
        if (!repository.existsById(carLeasingContract.getId())) {
            throw new EntityNotFoundException("Car Leasing Contract not found with id: " + carLeasingContract.getId());
        }
        return repository.save(carLeasingContract);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Car Leasing Contract not found with id: " + id);
        }
        repository.deleteById(id);
    }
}

