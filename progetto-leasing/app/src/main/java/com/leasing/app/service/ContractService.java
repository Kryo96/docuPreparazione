package com.leasing.app.service;

import com.leasing.app.dto.ContractDTO;
import com.leasing.app.model.Client;
import com.leasing.app.model.common.contract.Contract;
import com.leasing.app.repository.ClientRepository;
import com.leasing.app.repository.ContractRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final ClientRepository clientRepository;

    public ContractService(ContractRepository contractRepository, ClientRepository clientRepository) {
        this.contractRepository = contractRepository;
        this.clientRepository = clientRepository;
    }

    public List<Contract> findAll(){
        return contractRepository.findAll();
    }

    public Contract findById(Long id){
        return contractRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Invalid Id"));
    }

    public Contract createContract(ContractDTO contractDTO){
        Contract contract = new Contract();
        setUpContract(contractDTO, contract);
        contractRepository.save(contract);
        return contract;
    }


    public Contract updateById(Long id, ContractDTO contractDTO){
        Contract contract = contractRepository.findById(id).orElseThrow(()->new EntityNotFoundException("invalid Id"));
        setUpContract(contractDTO, contract);
        contractRepository.save(contract);
        return contract;
    }

    public void deleteById(Long id){
        if(contractRepository.findById(id).isPresent())
            contractRepository.deleteById(id);
        else
            throw new EntityNotFoundException("invalid id");
    }


    private void setUpContract(ContractDTO contractDTO, Contract contract) {
        contract.setContractNumber(contractDTO.getContractNumber() != null ? contractDTO.getContractNumber():null);
        contract.setStartDate(contractDTO.getStart_date() != null ? contractDTO.getStart_date():null);
        contract.setEndDate(contractDTO.getEnd_date() != null ? contractDTO.getEnd_date() : null);
        contract.setIsActive(true);

        if(contractDTO.getClient_id() != null){
            Client client = clientRepository.findById(contractDTO.getClient_id()).orElseThrow(()-> new EntityNotFoundException("Invalid client id"));
            contract.setClient(client);
        }
    }

}
