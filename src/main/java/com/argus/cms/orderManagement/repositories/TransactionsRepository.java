package com.argus.cms.orderManagement.repositories;

import com.argus.cms.orderManagement.dtos.TransactionSummaryDTO;
import com.argus.cms.orderManagement.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {


    @Query("SELECT new com.argus.cms.orderManagement.dtos.TransactionSummaryDTO(" +
            "   CASE " +
            "       WHEN t.fromEmployee.id = :userId THEN t.toEmployee.id " +
            "       ELSE t.fromEmployee.id " +
            "   END, " +
            "   ROUND(SUM(CASE " +
            "       WHEN t.fromEmployee.id = :userId THEN -t.amount " +
            "       ELSE t.amount " +
            "   END),2), " +
            "   u.userName) " +
            "FROM Transactions t " +
            "JOIN Users u ON u.id = " +
            "   CASE " +
            "       WHEN t.fromEmployee.id = :userId THEN t.toEmployee.id " +
            "       ELSE t.fromEmployee.id " +
            "   END " +
            "WHERE (t.fromEmployee.id = :userId OR t.toEmployee.id = :userId) " +
            "AND t.fromEmployee.id != t.toEmployee.id " +
            "GROUP BY u.userName, t.fromEmployee.id, t.toEmployee.id")
    List<TransactionSummaryDTO> getAllTransactionsOfUser(@Param("userId") Long userId);

}
