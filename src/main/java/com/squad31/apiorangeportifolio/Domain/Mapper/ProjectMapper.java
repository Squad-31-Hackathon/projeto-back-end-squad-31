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
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.UUID;

@Component
@Log4j2
public class ProjectMapper {

    @Autowired
    private UserRepository userRepository;

    public Project mapNewProject(ProjectRequestDTO request, byte[] image) throws IOException {

        User user = userRepository.findById(UUID.fromString(request.userUuid()))
                                  .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));



        return Project.builder()
                .user(user)
                .title(request.title())
                .tags(request.tags())
                .description(request.description())
                .link(request.link())
                .publishDate(Date.valueOf(LocalDate.now()))
                .image(image)
                .build();
    }

    public static ProjectResponseDTO mapProjectResponse(Project project) {

        String encodedImage = Base64.getEncoder().encodeToString(project.getImage());

        return new ProjectResponseDTO(
                project.getUuid().toString(),
                project.getTitle(),
                project.getTags(),
                project.getDescription(),
                project.getLink(),
                project.getPublishDate(),
                UserMapper.mapFromUserToUserResponse(project.getUser()),
                project.getImage()
        );
    }

}
