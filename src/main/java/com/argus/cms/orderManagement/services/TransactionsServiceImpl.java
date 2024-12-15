package com.argus.cms.orderManagement.services;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.dtos.TransactionSummaryDTO;
import com.argus.cms.orderManagement.entities.Transactions;
import com.argus.cms.orderManagement.repositories.TransactionsRepository;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {

    private TransactionsRepository transactionsRepository;
    private UserService userService;

    @Override
    public List<TransactionSummaryDTO> getAllTransactionsOfUser() throws RecordNotFoundException {
        Users user = userService.getCurrentUser();
        return transactionsRepository.getAllTransactionsOfUser(user.getId());

    }

    public Transactions getTransactionFromId(Long transactionId) throws RecordNotFoundException {
        return transactionsRepository.findById(transactionId).orElseThrow(()-> new RecordNotFoundException("Transaction with the id "+ transactionId + "not found"));
    }
}
