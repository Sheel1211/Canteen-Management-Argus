package com.argus.cms.orderManagement.controllers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.dtos.TransactionsResponseDTO;
import com.argus.cms.orderManagement.transformers.TransactionTransformer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
