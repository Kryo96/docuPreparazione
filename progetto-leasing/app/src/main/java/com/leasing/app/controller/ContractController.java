package com.leasing.app.controller;


import com.leasing.app.dto.ContractDTO;
import com.leasing.app.model.common.contract.Contract;
import com.leasing.app.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/all")
    public ResponseEntity<List<Contract>> findAll(){
        return ResponseEntity.ok(contractService.findAll());
    }

    @GetMapping("/findBy/{id}")
    public ResponseEntity<Contract> findById(@PathVariable Long id){
        return ResponseEntity.ok(contractService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody ContractDTO contractDTO){
        return ResponseEntity.ok(contractService.createContract(contractDTO).getId());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Long> update(@RequestBody ContractDTO contractDTO, @PathVariable Long id){
        return ResponseEntity.ok(contractService.updateById(id, contractDTO).getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        contractService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
