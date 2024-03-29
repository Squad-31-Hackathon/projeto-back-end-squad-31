package com.squad31.apiorangeportifolio.Domain.Mapper;

import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectResponseDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.Project.UpdateProjectRequest;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Repository.UserRepository;
import com.squad31.apiorangeportifolio.Exceptions.ImageProcessingException;
import com.squad31.apiorangeportifolio.Exceptions.ProjectNotFoundException;
import com.squad31.apiorangeportifolio.Utils.ImageUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;
import java.util.zip.DataFormatException;

@Component
@Log4j2
public class ProjectMapper {

    @Autowired
    private UserRepository userRepository;

    public Project mapNewProject(ProjectRequestDTO request) {

        User user = userRepository.findById(UUID.fromString(request.userUuid()))
                .orElseThrow(ProjectNotFoundException::new);


        return Project.builder()
                .user(user)
                .title(request.title())
                .tags(request.tags())
                .description(request.description())
                .link(request.link())
                .publishDate(Date.valueOf(LocalDate.now()))
                .image(request.image())
                .build();
    }

    public static ProjectResponseDTO mapProjectResponse(Project project) {


            return new ProjectResponseDTO(
                    project.getUuid().toString(),
                    project.getTitle(),
                    project.getTags(),
                    project.getDescription(),
                    project.getLink(),
                    project.getPublishDate(),
                    project.getImage(),
                    UserMapper.mapFromUserToUserResponse(project.getUser())
            );

    }

    public Project mapUpdateProject(UpdateProjectRequest updateProjectRequest) throws IOException {

        return Project.builder()
                .title(updateProjectRequest.title())
                .tags(updateProjectRequest.tags())
                .description(updateProjectRequest.description())
                .link(updateProjectRequest.link())
                .image(updateProjectRequest.image())
                .build();

    }

}


