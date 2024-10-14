package com.argus.cms.canteenManagement.entities;

import com.argus.cms.commonEntityFields.AuditEntity;
import com.argus.cms.userManagement.users.entities.Users;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_canteen_user")
@Data
public class CanteenUser extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="canteen_user_id", referencedColumnName = "id")
    private Users canteenUserId;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "canteen_id", referencedColumnName = "id")
    private Canteen canteen;
}
