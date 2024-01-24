package com.squad31.apiorangeportifolio.Controller.Request;

import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserResponseDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Mapper.UserMapper;
import com.squad31.apiorangeportifolio.Domain.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userData) {

        User user = userService.createUser(userData);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.mapFromUserToUserResponse(user));
    }
}
