package com.argus.cms.orderManagement.entities;

import com.argus.cms.userManagement.users.entities.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import com.argus.cms.userManagement.users.entities.Users;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.math.BigDecimal;
//
@Entity
@Table(name = "tbl_transactions")
@NoArgsConstructor
@Getter
@Setter
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "from_employee_id", nullable = false)
    private Users fromEmployee;

    @ManyToOne
    @JoinColumn(name = "to_employee_id", nullable = false)
    private Users toEmployee;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "status", nullable = false, length = 10)
    private String status = "PENDING";
}