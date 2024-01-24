package com.squad31.apiorangeportifolio.Domain.Mapper;

import com.squad31.apiorangeportifolio.Controller.Request.CreateProjectRequest;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Component
@Log4j2
public class ProjectMapper {

    @Autowired
    private UserRepository userRepository;

    public Project mapNewProject(CreateProjectRequest request) {

        User user = userRepository.findById(UUID.fromString(request.userUuid()))
                                  .orElseThrow(IllegalArgumentException::new); // quando criar a NotFoundException põe aqui

        byte[] processedImage = new byte[0];

        try {
            processedImage = request.image().getBytes();
        } catch (IOException e) {
            log.error("Erro ao processar imagem: {}", e.getMessage());
        }

        return Project.builder()
                .user(user)
                .title(request.title())
                .tags(request.tags())
                .description(request.description())
                .link(request.link())
                .image(processedImage)
                .publishDate(Date.valueOf(LocalDate.now()))
                .build();
    }

}
