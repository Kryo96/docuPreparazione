package com.leasing.app.service;

import com.leasing.app.dto.ProductContractDTO;
import com.leasing.app.model.Product;
import com.leasing.app.model.common.contract.Contract;
import com.leasing.app.model.common.contract.ProductContract;
import com.leasing.app.repository.ContractRepository;
import com.leasing.app.repository.ProductContractRepository;
import com.leasing.app.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductContractService {

    private final ProductContractRepository productContractRepository;
    private final ProductRepository productRepository;
    private final ContractRepository contractRepository;

    public ProductContractService(ProductContractRepository productContractRepository, ProductRepository productRepository, ContractRepository contractRepository) {
        this.productContractRepository = productContractRepository;
        this.productRepository = productRepository;
        this.contractRepository = contractRepository;
    }

    public List<ProductContract> findAll(){
        return productContractRepository.findAll();
    }


   public ProductContract findById(@NotNull Long id){
        return productContractRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Invalid Id"));
   }

   public ProductContract createProductContract(ProductContractDTO productContractDTO){
        ProductContract productContract = new ProductContract();
        setUpProductContract(productContractDTO, productContract);
        productContractRepository.save(productContract);
        return productContract;
    }

   public ProductContract updateById(@NotNull Long id, ProductContractDTO productContractDTO){
        ProductContract productContract = productContractRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("invalid id"));
        setUpProductContract(productContractDTO, productContract);
        productContractRepository.save(productContract);
            return productContract;
        }

    public void deleteById(@NotNull Long id){
        ProductContract productContract = productContractRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("invalid id"));
        productContractRepository.deleteById(id);
    }

    private void setUpProductContract(ProductContractDTO productContractDTO, ProductContract productContract) {
        if(productContractDTO.getContractId() != null){
            Contract contract = contractRepository
                    .findById(productContractDTO.getContractId())
                    .orElseThrow(()-> new EntityNotFoundException("contract id it's not valid"));
            productContract.setContract(contract);
        }

        if(productContractDTO.getProductId() != null){
            Product product = productRepository.findById(productContractDTO.getProductId())
                    .orElseThrow(()-> new EntityNotFoundException("product id it's not valid"));
            productContract.setProduct(product);
        }
    }
}
