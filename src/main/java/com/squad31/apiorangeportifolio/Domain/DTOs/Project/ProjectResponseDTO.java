package com.squad31.apiorangeportifolio.Domain.DTOs.Project;

import com.squad31.apiorangeportifolio.Domain.Entity.User;

import java.sql.Date;
import java.util.List;

public record ProjectResponseDTO(

        String uuid,
        String title,
        List<String> tags,
        String description,
        String link,
        Date publishDate,
        User owner,
        String image

) {
}
