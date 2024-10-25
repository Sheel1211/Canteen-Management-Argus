package com.argus.cms.canteenManagement.entities;

import com.argus.cms.audit.AuditEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_canteen")
@NoArgsConstructor
@Getter
@Setter
public class Canteen extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

//    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "canteen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CanteenManager> canteenManagers;
}