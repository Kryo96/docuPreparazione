package com.leasing.app.controller.common;

import com.leasing.app.model.common.CarLeasingContract;
import com.leasing.app.service.common.CarLeasingContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/clients/leasing/cars")
public class CarLeasingContractController {

    @Autowired
    private CarLeasingContractService service;

    @PostMapping
    public ResponseEntity<CarLeasingContract> createCarLeasingContract(@RequestBody CarLeasingContract carLeasingContract) throws URISyntaxException {
        CarLeasingContract savedCarLeasingContract = service.save(carLeasingContract);
        return ResponseEntity.created(new URI("/clients/leasing/cars/" + savedCarLeasingContract.getId()))
                .body(savedCarLeasingContract);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarLeasingContract> getCarLeasingContract(@PathVariable Long id) {
        CarLeasingContract carLeasingContract = service.findById(id);
        return ResponseEntity.ok(carLeasingContract);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarLeasingContract> updateCarLeasingContract(@PathVariable Long id, @RequestBody CarLeasingContract updatedContract) {
        updatedContract.setId(id);
        CarLeasingContract updated = service.update(updatedContract);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarLeasingContract(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
