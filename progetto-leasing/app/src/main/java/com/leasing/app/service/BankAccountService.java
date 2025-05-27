package com.leasing.app.service;
import com.leasing.app.dto.BankAccountDTO;
import com.leasing.app.model.Client;
import com.leasing.app.model.common.cash.BankAccount;
import com.leasing.app.model.common.cash.Transaction;
import com.leasing.app.repository.BankAccountRepository;
import com.leasing.app.repository.ClientRepository;
import com.leasing.app.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;

    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    public BankAccount findById(Long id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BankAccount not found with id: " + id));
    }

    public BankAccount create(BankAccountDTO dto) {
        BankAccount account = new BankAccount();
        setUpBankAccount(dto, account);
        return bankAccountRepository.save(account);
    }



    public BankAccount update(Long id, BankAccountDTO dto) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BankAccount not found with id: " + id));
        setUpBankAccount(dto, account);
        return bankAccountRepository.save(account);
    }

    public void delete(Long id) {
        if (!bankAccountRepository.existsById(id)) {
            throw new EntityNotFoundException("BankAccount not found with id: " + id);
        }
        bankAccountRepository.deleteById(id);
    }


    private void setUpBankAccount(BankAccountDTO dto, BankAccount account) {
        account.setIban(dto.getIban() != null ? dto.getIban() : null);
        account.setIntestatario(!dto.getIntestatario().isEmpty() ? dto.getIntestatario() : null);

        if (dto.getClient() != null) {
            Client client = clientRepository.findById(dto.getClient())
                    .orElseThrow(() -> new EntityNotFoundException("Client not found"));
            account.setClient(client);
        }

        if (dto.getMoviments() != null && !dto.getMoviments().isEmpty()) {
            List<Transaction> transactions = transactionRepository.findAllById(dto.getMoviments());
            account.setMoviments(transactions);
        }
    }
}
