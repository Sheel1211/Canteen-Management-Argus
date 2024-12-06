package com.argus.cms.orderManagement.mappers;

import com.argus.cms.orderManagement.dtos.TransactionsResponseDTO;
import com.argus.cms.orderManagement.entities.Transactions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TransactionMapper {

    public abstract TransactionsResponseDTO toResponseDTO(Transactions transactions);


}
