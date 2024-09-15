package org.example.financemanagement.models.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank(message = "cannot be null or empty")
        String login,

        @NotBlank(message = "cannot be null or empty")
        String password
) {
}
