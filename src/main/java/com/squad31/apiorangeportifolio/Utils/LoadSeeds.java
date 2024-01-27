package com.squad31.apiorangeportifolio.Utils;

import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Service.ProjectService;
import com.squad31.apiorangeportifolio.Domain.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
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

        Set<String> tags1 = new HashSet<>();
        tags1.add("UX");
        tags1.add("UI");

        ProjectRequestDTO projectRequestDTO = new ProjectRequestDTO(savedUser.getUuid().toString(), "projeto-teste", tags1, "projeto teste", "www.projeto.com");
        projectService.createNewProject(projectRequestDTO, "default_test_image".getBytes(StandardCharsets.UTF_8));

        Set<String> tags2 = new HashSet<>();
        tags2.add("BACKEND");
        tags2.add("FRONTEND");

        ProjectRequestDTO projectRequestDTO2 = new ProjectRequestDTO(savedUser.getUuid().toString(), "projeto-teste-2", tags2, "projeto teste 2", "www.projeto2.com");
        projectService.createNewProject(projectRequestDTO2, "default_test_image".getBytes(StandardCharsets.UTF_8));
        System.out.println("User id for tests: " + savedUser.getUuid());
    }
}
