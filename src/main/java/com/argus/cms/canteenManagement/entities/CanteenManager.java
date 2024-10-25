package com.argus.cms.canteenManagement.entities;

import com.argus.cms.audit.AuditEntity;
import com.argus.cms.userManagement.users.entities.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_canteen_manager")
@NoArgsConstructor
@Getter
@Setter
public class CanteenManager extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="canteen_manager_id", referencedColumnName = "id")
    private Users manager;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = false;

    @ManyToOne
    @JoinColumn(name = "canteen_id", referencedColumnName = "id")
    private Canteen canteen;
}
