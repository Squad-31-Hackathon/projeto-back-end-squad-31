package com.squad31.apiorangeportifolio.Domain.Mapper;

import com.squad31.apiorangeportifolio.Controller.Request.CreateProjectRequest;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@Component
public class ProjectMapper {

    public Project mapNewProject(CreateProjectRequest request) throws IOException {

        byte[] processedImage = request.image().getBytes();

        return Project.builder()
                .title(request.title())
                .tags(request.tags())
                .description(request.description())
                .link(request.link())
                .image(processedImage)
                .publishDate(Date.valueOf(LocalDate.now()))
                .build();
    }

}
