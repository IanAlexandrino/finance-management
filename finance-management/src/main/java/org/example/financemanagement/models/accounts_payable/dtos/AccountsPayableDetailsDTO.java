package org.example.financemanagement.models.accounts_payable.dtos;

import org.example.financemanagement.models.accounts_payable.AccountsPayable;

import java.time.LocalDate;

public record AccountsPayableDetailsDTO(
        Long id,
        String description,
        Double value,
        LocalDate dueDate,
        String status,
        String category,
        Long userId
    ) {

    public AccountsPayableDetailsDTO(AccountsPayable accountsPayable){
        this(
                accountsPayable.getId(),
                accountsPayable.getDescription(),
                accountsPayable.getValue(),
                accountsPayable.getDueDate(),
                String.valueOf(accountsPayable.getStatus()),
                accountsPayable.getCategory(),
                accountsPayable.getUser().getId()
        );
    }

}
