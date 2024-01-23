package com.squad31.apiorangeportifolio.Domain.Mapper;

import com.squad31.apiorangeportifolio.Controller.Request.CreateUserRequest;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapNewUser(CreateUserRequest request){
        return User.builder()
                .name("%s %s".formatted(request.name(), request.lastname()))
                .email(request.email())
                .password(request.password()) // Aqui tem que entrar a encripitação da senha
                .build();
    }

}
