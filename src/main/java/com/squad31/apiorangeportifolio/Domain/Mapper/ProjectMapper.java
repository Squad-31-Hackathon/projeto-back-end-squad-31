package com.squad31.apiorangeportifolio.Domain.Mapper;

import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectResponseDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Repository.UserRepository;
import com.squad31.apiorangeportifolio.Exceptions.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.UUID;

@Component
@Log4j2
public class ProjectMapper {

    @Autowired
    private UserRepository userRepository;

    public Project mapNewProject(ProjectRequestDTO request) {

        User user = userRepository.findById(UUID.fromString(request.userUuid()))
                                  .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        byte[] decodedImage = new byte[0];

        try {
            decodedImage = request.image().getBytes();
        } catch (IOException e) {
            log.error("Erro ao processar imagem");
        }

        return Project.builder()
                .user(user)
                .title(request.title())
                .tags(request.tags())
                .description(request.description())
                .link(request.link())
                .image(decodedImage)
                .publishDate(Date.valueOf(LocalDate.now()))
                .build();
    }

    public static ProjectResponseDTO mapProjectResponse(Project project) {

        // TODO: fazer o controller para resgatar essa imagem
        String imagePath = "/project/image/%s".formatted(project.getUuid());

        return new ProjectResponseDTO(
                project.getUuid().toString(),
                project.getTitle(),
                project.getTags(),
                project.getDescription(),
                project.getLink(),
                project.getPublishDate(),
                project.getUser(),
                imagePath
        );
    }

}
