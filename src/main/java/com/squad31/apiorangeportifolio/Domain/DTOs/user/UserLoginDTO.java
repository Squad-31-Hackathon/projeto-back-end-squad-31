package com.squad31.apiorangeportifolio.Domain.DTOs.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserLoginDTO(

        String email,

        String password
) {
}
