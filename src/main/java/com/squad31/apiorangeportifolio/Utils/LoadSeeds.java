package com.squad31.apiorangeportifolio.Utils;

import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.User.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Service.ProjectService;
import com.squad31.apiorangeportifolio.Domain.Service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Component
@Log4j2
public class LoadSeeds {
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    private User savedUser;
    private Project savedProject1;
    private Project savedProject2;

    public void loadUsers() {
        UserRequestDTO adminUser = new UserRequestDTO("user", "admin", "admin@email.com", "admin123");
        savedUser = userService.create(adminUser);

        Set<String> tags1 = new HashSet<>();
        tags1.add("UX");
        tags1.add("UI");

        ProjectRequestDTO projectRequestDTO = new ProjectRequestDTO(savedUser.getUuid().toString(), "projeto-teste", tags1, "projeto teste", "www.projeto.com");
        savedProject1 = projectService.createNewProject(projectRequestDTO);

        Set<String> tags2 = new HashSet<>();
        tags2.add("BACKEND");
        tags2.add("FRONTEND");

        ProjectRequestDTO projectRequestDTO2 = new ProjectRequestDTO(savedUser.getUuid().toString(), "projeto-teste-2", tags2, "projeto teste 2", "www.projeto2.com");
        savedProject2 = projectService.createNewProject(projectRequestDTO2);

        System.out.println("User id for tests: " + savedUser.getUuid());
    }

    @Component
    class ShutdownListener implements ApplicationListener<ContextClosedEvent> {
        @Override
        public void onApplicationEvent(ContextClosedEvent event) {
            projectService.deleteProject(savedProject1.getUuid());
            projectService.deleteProject(savedProject2.getUuid());
            userService.deleteUser(savedUser.getUuid());

            log.info("Entidades de testes excluídas, finalizando o serviço de persitência");

        }

        @Override
        public boolean supportsAsyncExecution() {
            return ApplicationListener.super.supportsAsyncExecution();
        }

    }

}


