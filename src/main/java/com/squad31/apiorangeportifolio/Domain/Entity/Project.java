package com.squad31.apiorangeportifolio.Domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private UUID uuid;

    @Column (name = "title", nullable = false)
    @NotBlank
    private String title;

    @ElementCollection
    @NotBlank
    private Set<String> tags;

    @Column (name = "description", length = 400)
    @NotBlank
    private String description;

    @Column (name = "link", nullable = false)
    @NotBlank
    private String link;

    @Column (name = "image", nullable = false, length = 1000)
    private byte[] image;

    @Column (name = "publish_date", nullable = false)
    @NotBlank
    private Date publishDate;

    @ManyToOne
    @JoinColumn (name = "user_uuid", nullable = false)
    @NotBlank
    private User user;
}
