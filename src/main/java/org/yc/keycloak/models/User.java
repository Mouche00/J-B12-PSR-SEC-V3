package org.yc.keycloak.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.yc.keycloak.utils.enums.Role;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private String role;

    @PrePersist
    public void onCreate() {
        role = Role.USER.getAuthority();
    }

}