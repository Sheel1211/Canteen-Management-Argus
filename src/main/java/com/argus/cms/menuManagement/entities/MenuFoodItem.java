package com.argus.cms.menuManagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_menu_food_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuFoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_id", insertable = false, updatable = false) // Prevent writes from this side
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "food_item_id")
    private FoodItem foodItem;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(name = "quantity")
    private Integer quantity;
}