package com.mrc_intern_management.api.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "intern_users")
public class UserModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id", nullable = false)
        private Long userId;

        @Column(name = "name")
        private String name;

        @Column(name = "email")
        private String email;

        @Column(name = "phone")
        private Long phone;

        @Column(name = "gender")
        @Enumerated(EnumType.STRING)
        private UserGender gender;

        @Column(name = "department")
        private String department;

        @Column(name = "address")
        private String address;

        @Column(name = "org_id")
        private Long orgId;

        @Column(name = "intern_id")
        private Long internId;

        @Column(name = "role", length = 40)
        @Enumerated(EnumType.STRING)
        private UserRole role;

}
