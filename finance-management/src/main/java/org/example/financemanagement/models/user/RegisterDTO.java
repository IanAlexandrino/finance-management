package org.example.financemanagement.models.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
        @NotBlank
        String login,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String name,

        @NotBlank
        String password
) {
}
