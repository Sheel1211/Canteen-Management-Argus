package com.argus.cms.menuManagement.entities;

import com.argus.cms.audit.AuditEntity;
import com.argus.cms.canteenManagement.entities.Canteen;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_menus")
@NoArgsConstructor
@Getter
@Setter
public class Menu extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Menu name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "canteen_id")
    private Canteen canteen;

    @Column(name = "menu_date")
    @FutureOrPresent(message = "Menu date must be today or in the future")
    private LocalDate date_of_menu;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbl_menu_food_items",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "food_item_id")
    )

    @NotEmpty(message = "At least one food item is required in a menu")
    private List<FoodItem> foodItems;
}

