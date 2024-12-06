package com.argus.cms.orderManagement.entities;

import com.argus.cms.menuManagement.entities.FoodItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_orders_food_common")
@NoArgsConstructor
@Getter
@Setter
public class OrderFoodCommon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_item_id", nullable = false)
    private FoodItem foodItem;

    @Column(name = "quantity")
    private Integer quantity = 0;
}
