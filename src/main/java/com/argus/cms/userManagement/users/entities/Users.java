package com.argus.cms.userManagement.users.entities;

import com.argus.cms.audit.AuditEntity;
import com.argus.cms.userManagement.roles.entities.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer balance = 0;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_authorities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
    @JsonIgnore
    private Set<Roles> roles = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (balance == null) {
            balance = 0;
        }
        if (isActive == null) {
            isActive = true;
        }
    }

}
