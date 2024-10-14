package com.argus.cms.canteenManagement.entities;

import com.argus.cms.commonEntityFields.AuditEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_canteen")
@Data
public class Canteen extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToOne(mappedBy = "canteen", cascade = CascadeType.ALL)
    private CanteenUser canteenUser;
}