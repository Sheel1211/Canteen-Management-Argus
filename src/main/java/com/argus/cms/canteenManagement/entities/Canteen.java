package com.argus.cms.canteenManagement.entities;

import com.argus.cms.audit.AuditEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Canteen Name must not be blank !")
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @JsonIgnore
    @OneToMany(mappedBy = "canteen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CanteenUser> canteenUsers;
    
}