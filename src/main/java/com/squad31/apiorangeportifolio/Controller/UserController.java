package com.squad31.apiorangeportifolio.Controller;

import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectResponseDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.User.UserResponseDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Mapper.ProjectMapper;
import com.squad31.apiorangeportifolio.Domain.Mapper.UserMapper;
import com.squad31.apiorangeportifolio.Domain.Service.UserService;
import com.squad31.apiorangeportifolio.Exceptions.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "token")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Realiza a consulta de um usuário pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário com este ID não encontrado", content = {@Content(schema = @Schema(implementation = ErrorDTO.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.mapFromUserToUserResponse(user));
    }
    @Operation(summary = "Realiza a consulta dos projetos do usuário autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projetos encontrados com sucesso"),
    })
    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponseDTO>> getAuthenticatedUserProjects() {
        log.info("Buscando projetos do usuário logado");
        List<ProjectResponseDTO> projects = userService.getAuthenticatedUserProjects().stream()
                .map(ProjectMapper::mapProjectResponse)
                .toList();

        log.info("Retornando projetos do usuário logado");
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

}
