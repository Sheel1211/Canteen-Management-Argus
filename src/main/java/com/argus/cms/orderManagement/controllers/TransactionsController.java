package com.argus.cms.orderManagement.controllers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.dtos.TransactionSummaryDTO;
import com.argus.cms.orderManagement.dtos.TransactionsResponseDTO;
import com.argus.cms.orderManagement.transformers.TransactionTransformer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionsController {

    private TransactionTransformer transactionTransformer;

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionsResponseDTO> getTransactionById(@PathVariable Long transactionId) throws RecordNotFoundException
    {
        TransactionsResponseDTO transactionsResponseDTO = transactionTransformer.getTransactionById(transactionId);
        return new ResponseEntity<>(transactionsResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<TransactionSummaryDTO>> getAllTransactionsOfUser() throws RecordNotFoundException {
        List<TransactionSummaryDTO> transactions = transactionTransformer.getAllTransactionsOfUser();
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }
}