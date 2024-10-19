package com.argus.cms.menuManagement.entities;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.audit.AuditEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tbl_category")
@Data
@NoArgsConstructor
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
