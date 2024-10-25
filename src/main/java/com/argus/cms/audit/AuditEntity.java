package com.argus.cms.audit;

import com.argus.cms.userManagement.users.entities.Users;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by", updatable = false)
    private Users createdBy;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "updated_by", updatable = false) // Recommended to control column name
    private Users updatedBy;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
