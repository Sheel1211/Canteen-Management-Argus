package com.argus.cms.menuManagement.entities;

import com.argus.cms.audit.AuditEntity;
import com.argus.cms.canteenManagement.entities.Canteen;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_category")
@NoArgsConstructor
@Getter
@Setter
public class Category extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type")
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="canteen_id",referencedColumnName = "id")
    private Canteen canteen;

    @ManyToMany(mappedBy = "categories")
    private List<FoodItem> foodItems;
}
