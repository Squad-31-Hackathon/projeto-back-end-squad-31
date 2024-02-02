package TestUtils;

import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectRequestDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Entity.User;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class ProjectTestUtils {

    public static final UUID userUUID = UUID.randomUUID();
    public static final UUID projectUUID = UUID.randomUUID();

    public static Project ValidProject(){

        return Project.builder()
                .uuid(projectUUID)
                .title("título do projeto")
                .tags(Set.of("UX", "UI"))
                .description("descrição do projeto")
                .link("link do projeto")
                .publishDate(Date.valueOf(LocalDate.now()))
                .user(User.builder()
                        .uuid(userUUID)
                        .name("Usuário")
                        .lastName("Admin")
                        .email("admin@admin.com")
                        .password("admin123")
                        .profileImage("imagem".getBytes(StandardCharsets.UTF_8))
                        .build()
                ).build();

    }

    public static ProjectRequestDTO ValidProjectDTO(){

        return new ProjectRequestDTO(userUUID.toString(),
                "título do projeto", Set.of("UX, UI"),
                "descrição do projeto",
                    "link do projeto",
                "teste"
                );
    }

    public static ProjectRequestDTO InvalidUserProjectDTO(){

        return new ProjectRequestDTO(UUID.randomUUID().toString(),
                "título do projeto", Set.of("UX, UI"),
                "descrição do projeto",
                "link do projeto",
                "teste"
        );
    }

}
