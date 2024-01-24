package com.squad31.apiorangeportifolio.Domain.DTOs.user;

import lombok.Data;


public record UserRequestDTO (

        String name,

        String lastName,

        String email,

        String password

) {}


