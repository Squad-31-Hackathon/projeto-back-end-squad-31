package com.squad31.apiorangeportifolio.Domain.Mapper;

import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectResponseDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Repository.UserRepository;
import com.squad31.apiorangeportifolio.Exceptions.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
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

    public Project mapNewProject(ProjectRequestDTO request) {

        User user = userRepository.findById(UUID.fromString(request.userUuid()))
                                  .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

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

    public static ProjectResponseDTO mapProjectResponse(Project project){

        ProjectResponseDTO response = null;
        BeanUtils.copyProperties(project, response);

        return response;
    }

}
