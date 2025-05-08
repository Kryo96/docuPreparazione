package com.leasing.app.service.common;

import com.leasing.app.model.common.contract.LeasingContract;
import com.leasing.app.repository.common.LeasingContractRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LeasingContractService {
    @Autowired
    private LeasingContractRepository repository;

    public LeasingContract save(LeasingContract contract) {
        return repository.save(contract);
    }

    public List<LeasingContract> findAll() {
        return repository.findAll();
    }

    public LeasingContract findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contract not found"));
    }

    public LeasingContract update(LeasingContract leasingContract) {
        if (!repository.existsById(leasingContract.getId())) {
            throw new EntityNotFoundException("Contract not found with id: " + leasingContract.getId());
        }
        return repository.save(leasingContract);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Contract not found with id: " + id);
        }
        repository.deleteById(id);
    }

}
