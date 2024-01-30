package com.squad31.apiorangeportifolio.Domain.Mapper;

import com.squad31.apiorangeportifolio.Domain.DTOs.User.UserResponseDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Log4j2
public class UserMapper {

    public static UserResponseDTO mapFromUserToUserResponse(User user) {

        return new UserResponseDTO(
                user.getUuid().toString(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                Arrays.toString(user.getProfileImage()));
    }
}
