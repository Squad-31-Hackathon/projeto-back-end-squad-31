package com.squad31.apiorangeportifolio.Domain.DTOs.Project;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public record ProjectRequestDTO(

        String userUuid,
        String title,
        Set<String> tags,
        String description,
        String link

) {
}
