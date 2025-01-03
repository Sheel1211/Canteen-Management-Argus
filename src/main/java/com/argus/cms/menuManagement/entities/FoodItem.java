package com.argus.cms.menuManagement.entities;

import com.argus.cms.audit.AuditEntity;
import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.orderManagement.entities.OrderFoodCommon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_food_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    @Min(value = 0, message = "Price cannot be negative")
    private Double price;

    @NotEmpty(message = "Quantity must not be empty")
    private String quantityPerPlate;

    @Version
    private Integer version;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "canteen_id" , referencedColumnName = "id")
    @NotNull(message = "Canteen must be specified")
    private Canteen canteen;

    @JsonIgnore
    @ManyToMany
    @NotEmpty(message = "Food item must belong to at least one category")
    @JoinTable(name = "tbl_food_item_category", joinColumns = @JoinColumn(name = "food_item_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "foodItem", cascade = CascadeType.ALL)
    private List<OrderFoodCommon> orderItems;

    @OneToMany(mappedBy = "foodItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuFoodItem> menuFoodItems;
}