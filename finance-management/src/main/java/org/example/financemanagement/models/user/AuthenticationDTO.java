package org.example.financemanagement.models.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
