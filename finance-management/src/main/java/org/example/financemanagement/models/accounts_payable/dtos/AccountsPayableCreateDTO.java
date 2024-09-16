package org.example.financemanagement.models.accounts_payable.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AccountsPayableCreateDTO(

        @NotBlank(message = "cannot be null or empty")
        @Size(max = 255, message = "too long description")
        String description,

        @NotNull(message = "cannot be null")
        @Positive(message = "the value must be positive")
        Double value,

        @NotBlank(message = "cannot be null or empty")
        String dueDate,

        @NotBlank(message = "cannot be null or empty")
        String status,

        @NotBlank(message = "cannot be null or empty")
        @Size(max = 255, message = "too long category")
        String category,

        @NotNull(message = "cannot be null")
        Long userId

) {
}
