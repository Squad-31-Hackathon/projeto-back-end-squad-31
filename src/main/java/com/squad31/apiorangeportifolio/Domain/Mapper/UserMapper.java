package com.squad31.apiorangeportifolio.Domain.Mapper;

import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserResponseDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UserMapper {

    public static UserResponseDTO mapFromUserToUserResponse(User user) {

        return new UserResponseDTO(
                user.getUuid().toString(),
                user.getName(),
                user.getLastName(),
                user.getEmail());
    }
}
