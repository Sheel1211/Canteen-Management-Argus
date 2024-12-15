package com.argus.cms.orderManagement.transformers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.dtos.TransactionSummaryDTO;
import com.argus.cms.orderManagement.dtos.TransactionsResponseDTO;
import com.argus.cms.orderManagement.entities.Transactions;
import com.argus.cms.orderManagement.mappers.TransactionMapper;
import com.argus.cms.orderManagement.services.TransactionsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<TransactionSummaryDTO> getAllTransactionsOfUser() throws RecordNotFoundException {
        return transactionsService.getAllTransactionsOfUser();
    }
}
