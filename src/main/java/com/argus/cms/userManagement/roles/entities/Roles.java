package com.argus.cms.userManagement.roles.entities;

import com.argus.cms.audit.AuditEntity;
import com.argus.cms.userManagement.users.entities.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_authorities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Users> users = new HashSet<>();
}
