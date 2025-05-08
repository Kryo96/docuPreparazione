package com.leasing.app.controller.common;

import com.leasing.app.model.common.contract.LeasingContract;
import com.leasing.app.service.common.LeasingContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/clients/leasing")
public class LeasingContractController {

    @Autowired
    private LeasingContractService service;

    public LeasingContractController(LeasingContractService leasingContractService) {
        this.service = leasingContractService;
    }

    @GetMapping
    public List<LeasingContract> getAllLeasingContracts() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<LeasingContract> createLeasingContract(@RequestBody LeasingContract leasingContract) throws URISyntaxException {
        LeasingContract savedLeasingContract = service.save(leasingContract);
        return ResponseEntity.created(new URI("/clients/leasing/contracts/" + savedLeasingContract.getId()))
                .body(savedLeasingContract);
    }

    @GetMapping("/contracts/{id}")
    public ResponseEntity<LeasingContract> getLeasingContractById(@PathVariable Long id) {
        LeasingContract contract = service.findById(id);
        return ResponseEntity.ok(contract);
    }

    @PutMapping("/contracts/{id}")
    public ResponseEntity<LeasingContract> updateLeasingContractById(@PathVariable Long id, @RequestBody LeasingContract updatedContract) {
        updatedContract.setId(id);
        LeasingContract updated = service.update(updatedContract);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/contracts/{id}")
    public ResponseEntity<Void> deleteLeasingContract(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

