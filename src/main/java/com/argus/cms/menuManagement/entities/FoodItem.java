package com.argus.cms.menuManagement.entities;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.audit.AuditEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
    private String name;
    private String description;
    private BigDecimal price;

    private Integer quantity;

    @Version
    private Integer version;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "canteen_id" , referencedColumnName = "id")
    private Canteen canteen;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tbl_food_item_category", joinColumns = @JoinColumn(name = "food_item_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();
}