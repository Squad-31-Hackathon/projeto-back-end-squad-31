package com.squad31.apiorangeportifolio.Controller;

import com.squad31.apiorangeportifolio.Domain.DTOs.User.UserLoginDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.User.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.User.UserResponseDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.User.UserTokenDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Mapper.UserMapper;
import com.squad31.apiorangeportifolio.Domain.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> login(@Valid @RequestBody UserLoginDTO userLoginData) {

        String token = authService.login(userLoginData);

        return ResponseEntity.status(HttpStatus.OK).body(new UserTokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO userData) {
        User user = authService.register(userData);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.mapFromUserToUserResponse(user));
    }
}
