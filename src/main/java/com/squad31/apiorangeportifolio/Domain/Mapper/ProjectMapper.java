package com.squad31.apiorangeportifolio.Domain.Mapper;

import com.squad31.apiorangeportifolio.Controller.Request.CreateProjectRequest;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@Component
@Log4j2
public class ProjectMapper {

    public Project mapNewProject(CreateProjectRequest request) {

        byte[] processedImage = new byte[0];

        try {
            processedImage = request.image().getBytes();
        } catch (IOException e) {
            log.error("Erro ao processar imagem: {}", e.getMessage());
        }

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
