package com.leasing.app.controller;

import com.azure.core.annotation.Get;
import com.azure.core.annotation.PathParam;
import com.leasing.app.dto.ProductContractDTO;
import com.leasing.app.model.common.contract.ProductContract;
import com.leasing.app.service.ProductContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/productContract")
public class ProductContractController {

    private final ProductContractService productContractService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductContract>> getAll(){
        return ResponseEntity.ok(productContractService.findAll());
    }

    @GetMapping("/findBy/{id}")
    public ResponseEntity<ProductContract> findById(@PathVariable Long id){
        return ResponseEntity.ok(productContractService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ProductContract> createProductContract(@RequestBody ProductContractDTO productContractDTO){
        return ResponseEntity.ok(productContractService.createProductContract(productContractDTO));
    }

    @PutMapping("/updateBy/{id}")
    public ResponseEntity<ProductContract> updateById(@PathVariable Long id,
                                                      @RequestBody ProductContractDTO productContractDTO){
        return ResponseEntity.ok(productContractService.updateById(id,productContractDTO));
    }

    @DeleteMapping("/deleteBy/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        productContractService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
