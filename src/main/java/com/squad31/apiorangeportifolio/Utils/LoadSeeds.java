package com.squad31.apiorangeportifolio.Utils;

import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Service.ProjectService;
import com.squad31.apiorangeportifolio.Domain.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class LoadSeeds {
    @Autowired
    private UserService userService;

    @Autowired
    ProjectService projectService;

    public void loadUsers() {
        UserRequestDTO adminUser = new UserRequestDTO("user", "admin", "admin@email.com", "admin123");
        User savedUser = userService.create(adminUser);

        Set<String> tags = new HashSet<>();
        tags.add("UX");

        ProjectRequestDTO projectRequestDTO = new ProjectRequestDTO(savedUser.getUuid().toString(), "projeto-teste", tags, "projeto teste", "www.projeto.com", "imagem");
        projectService.createNewProject(projectRequestDTO);

    }
}
