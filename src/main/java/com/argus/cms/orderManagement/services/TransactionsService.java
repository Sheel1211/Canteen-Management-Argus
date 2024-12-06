package com.argus.cms.orderManagement.services;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.entities.Transactions;

public interface TransactionsService {

    Transactions getTransactionFromId(Long transactionId) throws RecordNotFoundException;

}
