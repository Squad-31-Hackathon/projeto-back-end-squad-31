package com.squad31.apiorangeportifolio.Domain.DTOs.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


public record UserRequestDTO (


        String name,

        String lastName,

        String email,

        String password

) {}


