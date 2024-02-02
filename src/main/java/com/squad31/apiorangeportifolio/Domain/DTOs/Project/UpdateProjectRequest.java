package com.squad31.apiorangeportifolio.Domain.DTOs.Project;

import java.util.Set;

public record UpdateProjectRequest(

        String projectUuid,
        String title,
        Set<String> tags,
        String description,
        String link

) {
}
