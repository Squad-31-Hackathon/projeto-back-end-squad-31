package com.squad31.apiorangeportifolio.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String uuid;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "email", nullable = false)
    private String email;

    @Column (name = "password", nullable = false)
    private String password; // Não sei como vai ficar após o spring security

    @OneToMany (mappedBy = "user")
    private List<Project> projects;

    @Column (name = "profile_image", length = 1000)
    private byte[] profileImage;
}
