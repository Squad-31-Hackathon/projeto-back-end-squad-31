package com.squad31.apiorangeportifolio.Controller;

import com.squad31.apiorangeportifolio.Domain.DTOs.User.UserLoginDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.User.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.User.UserResponseDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.User.UserTokenDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Mapper.UserMapper;
import com.squad31.apiorangeportifolio.Domain.Service.AuthService;
import com.squad31.apiorangeportifolio.Exceptions.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Realiza o login de um usuário pelo seu e-mail e senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso", content = {@Content(schema = @Schema(implementation = UserTokenDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Usuário inexistente ou senha inválida", content = {@Content(schema = @Schema(implementation = ErrorDTO.class))})
    })
    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> login(@Valid @RequestBody UserLoginDTO userLoginData) {
        log.info("Inicando autenticação de usuário");
        String token = authService.login(userLoginData);

        return ResponseEntity.status(HttpStatus.OK).body(new UserTokenDTO(token));
    }

    @Operation(summary = "Realiza o registro de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário com este email já cadastrado!", content = {@Content(schema = @Schema(implementation = ErrorDTO.class))})
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO userData) {
        log.info("Inicando registro de usuário");
        User user = authService.register(userData);

        log.info("Usuário cadastrado com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.mapFromUserToUserResponse(user));
    }
}
