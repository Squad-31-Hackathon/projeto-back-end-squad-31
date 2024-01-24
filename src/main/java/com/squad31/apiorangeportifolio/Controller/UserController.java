package com.squad31.apiorangeportifolio.Controller;

import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserResponseDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Mapper.UserMapper;
import com.squad31.apiorangeportifolio.Domain.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO userData) {

        User user = userService.create(userData);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.mapFromUserToUserResponse(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.mapFromUserToUserResponse(user));
    }
}
