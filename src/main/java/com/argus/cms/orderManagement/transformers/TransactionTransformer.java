package com.argus.cms.orderManagement.transformers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.dtos.TransactionsResponseDTO;
import com.argus.cms.orderManagement.entities.Transactions;
import com.argus.cms.orderManagement.mappers.TransactionMapper;
import com.argus.cms.orderManagement.services.TransactionsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransactionTransformer {

    private TransactionsService transactionsService;
    private TransactionMapper transactionMapper;
    public TransactionsResponseDTO getTransactionById(Long transactionId) throws RecordNotFoundException
    {
        Transactions transactions = transactionsService.getTransactionFromId(transactionId);
        return transactionMapper.toResponseDTO(transactions);
    }
}
