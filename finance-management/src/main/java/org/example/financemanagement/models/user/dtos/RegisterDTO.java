package org.example.financemanagement.models.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
        @NotBlank(message = "cannot be null or empty")
        String login,

        @Email(message = "must be a valid email address")
        @NotBlank(message = "cannot be null or empty")
        String email,

        @NotBlank(message = "cannot be null or empty")
        String name,

        @NotBlank(message = "cannot be null or empty")
        String password
) {
}
