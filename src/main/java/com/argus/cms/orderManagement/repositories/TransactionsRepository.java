package com.argus.cms.orderManagement.repositories;

import com.argus.cms.orderManagement.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {
}
