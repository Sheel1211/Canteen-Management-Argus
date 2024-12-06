package com.argus.cms.orderManagement.services;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.entities.Transactions;
import com.argus.cms.orderManagement.repositories.TransactionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {

    private TransactionsRepository transactionsRepository;

    public Transactions getTransactionFromId(Long transactionId) throws RecordNotFoundException {
        return transactionsRepository.findById(transactionId).orElseThrow(()-> new RecordNotFoundException("Transaction with the id "+ transactionId + "not found"));
    }
}
