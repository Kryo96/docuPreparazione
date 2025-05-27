package com.leasing.app.controller;

import com.leasing.app.dto.BankAccountDTO;
import com.leasing.app.model.common.cash.BankAccount;
import com.leasing.app.repository.BankAccountRepository;
import com.leasing.app.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping("/all")
    public ResponseEntity<List<BankAccount>> findAll(){
        return ResponseEntity.ok(bankAccountService.findAll());
    }

    @GetMapping("/findBy/{id}")
    public ResponseEntity<BankAccount> findById(@PathVariable Long id){
        return ResponseEntity.ok(bankAccountService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody BankAccountDTO bankAccountDTO){
        return ResponseEntity.ok(bankAccountService.create(bankAccountDTO).getId());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Long> update(@RequestBody BankAccountDTO bankAccountDTO, @PathVariable Long id){
        return ResponseEntity.ok(bankAccountService.update(id, bankAccountDTO).getId());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bankAccountService.delete(id);
        return ResponseEntity.ok().build();
    }
}
