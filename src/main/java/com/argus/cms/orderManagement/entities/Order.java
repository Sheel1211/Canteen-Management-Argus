package com.argus.cms.orderManagement.entities;

import com.argus.cms.audit.AuditEntity;
import com.argus.cms.menuManagement.entities.Menu;
import com.argus.cms.userManagement.users.entities.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_orders")
@NoArgsConstructor
@Getter
@Setter
public class Order extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderer_id")
    private Users orderer;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private Double orderAmount;

    private Boolean isCancelled = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItems> orderItems;
}
