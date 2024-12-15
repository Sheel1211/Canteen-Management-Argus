package com.argus.cms.orderManagement.services;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.dtos.TransactionSummaryDTO;
import com.argus.cms.orderManagement.entities.Transactions;

import java.util.List;

public interface TransactionsService {

    Transactions getTransactionFromId(Long transactionId) throws RecordNotFoundException;
    List<TransactionSummaryDTO> getAllTransactionsOfUser() throws RecordNotFoundException;
}
