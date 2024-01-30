package com.squad31.apiorangeportifolio.Domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table (name = "projects")
public class Project {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @NotNull
    private UUID uuid;

    @Column (name = "title")
    @NotBlank
    private String title;

    @ElementCollection
    @NotNull
    private Set<String> tags;

    @Column (name = "description", length = 400)
    @NotBlank
    private String description;

    @Column (name = "link")
    @NotBlank
    private String link;

    @Column (name = "image", length = 1000000) // 1MB
    @NotNull
    private byte[] image;

    @Column (name = "publish_date")
    @NotNull
    private Date publishDate;

    @ManyToOne
    @JoinColumn (name = "user_uuid", nullable = false)
    @NotNull
    private User user;
}
