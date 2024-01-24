package com.squad31.apiorangeportifolio.Controller.Request;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public record CreateProjectRequest(

        String userUuid,
        String title,
        Set<String> tags,
        String description,
        String link,
        MultipartFile image

) {
}
