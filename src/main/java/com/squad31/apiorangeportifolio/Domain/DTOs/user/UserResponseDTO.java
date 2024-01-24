package com.squad31.apiorangeportifolio.Domain.DTOs.user;

import java.util.UUID;

public record UserResponseDTO(

        String id,

        String name,

        String lastName,

        String email
) {



}
