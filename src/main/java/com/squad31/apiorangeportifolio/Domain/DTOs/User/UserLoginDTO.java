package com.squad31.apiorangeportifolio.Domain.DTOs.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record UserLoginDTO(

        @NotBlank(message = "Email é necessário")
        @Email(message = "Deve ser um email válido")
        String email,

        @NotBlank(message = "Senha é necessária")
        String password
) {
}
