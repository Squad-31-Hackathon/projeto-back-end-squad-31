package com.squad31.apiorangeportifolio.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table (name = "projects")
public class Project {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String uuid;

    @Column (name = "title", nullable = false)
    private String title;

    @ElementCollection
    private Set<String> tags;

    @Column (name = "description", length = 400)
    private String description;

    @Column (name = "link", nullable = false)
    private String link;

    @Column (name = "image", nullable = false, length = 1000)
    private byte[] image;

    @Column (name = "publish_date", nullable = false)
    private Date publishDate;

    @ManyToOne
    @JoinColumn (name = "user_uuid", nullable = false)
    private User user;
}
