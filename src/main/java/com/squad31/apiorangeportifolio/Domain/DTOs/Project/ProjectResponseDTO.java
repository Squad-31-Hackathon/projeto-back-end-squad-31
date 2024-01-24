package com.squad31.apiorangeportifolio.Domain.DTOs.Project;

import com.squad31.apiorangeportifolio.Domain.Entity.User;

import java.sql.Date;
import java.util.Set;

public record ProjectResponseDTO(

        String uuid,
        String title,
        Set<String> tags,
        String description,
        String link,
        Date publishDate,
        User owner,
        String imagePath

) {
}
