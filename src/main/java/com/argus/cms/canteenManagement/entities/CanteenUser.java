package com.argus.cms.canteenManagement.entities;

import com.argus.cms.audit.AuditEntity;
import com.argus.cms.canteenManagement.CanteenUserStatus;
import com.argus.cms.userManagement.users.entities.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_canteen_user")
@NoArgsConstructor
@Getter
@Setter
public class CanteenUser extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="canteen_user_id", referencedColumnName = "id")
    private Users user;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = false;

    @ManyToOne
    @JoinColumn(name = "canteen_id", referencedColumnName = "id")
    private Canteen canteen;

    @Column(name = "isOwner")
    private boolean isOwner = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CanteenUserStatus status = CanteenUserStatus.REQUESTED;
}