package com.argus.cms.orderManagement.entities;

import com.argus.cms.batchManagement.entity.Batch;
import com.argus.cms.orderManagement.OrderStatus;
import com.argus.cms.userManagement.users.entities.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_orders_items")
@NoArgsConstructor
@Getter
@Setter
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private Users receiver;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    private Double amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_items_id")
    private List<OrderFoodCommon> orderedFoodItems;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="transaction_id")
    private Transactions transaction;

//    @ManyToOne
//    @JoinColumn(name = "canteen_id")
//    private Canteen canteen;
}