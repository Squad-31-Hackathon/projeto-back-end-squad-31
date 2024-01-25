package com.squad31.apiorangeportifolio.Domain.DTOs.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;




public record UserRequestDTO (@NotBlank(message = "Usuário deve conter nome")
                              String name,

                              @NotBlank(message = "Usuário deve conter sobrenome")
                              String lastName,

                              @Email(message = "Usuário deve conter email válido")
                              String email,

                              @NotBlank(message = "Usuário deve conter senha")
                              String password) {}


