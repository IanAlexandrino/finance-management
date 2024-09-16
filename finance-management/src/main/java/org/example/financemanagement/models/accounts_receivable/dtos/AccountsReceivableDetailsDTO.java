package org.example.financemanagement.models.accounts_receivable.dtos;

import org.example.financemanagement.models.accounts_receivable.AccountsReceivable;

import java.time.LocalDate;

public record AccountsReceivableDetailsDTO(
        Long id,
        String description,
        Double value,
        LocalDate dateOfReceipt,
        String status,
        String category,
        Long userId
    ) {

    public AccountsReceivableDetailsDTO(AccountsReceivable accountsReceivable){
        this(
                accountsReceivable.getId(),
                accountsReceivable.getDescription(),
                accountsReceivable.getValue(),
                accountsReceivable.getDateOfReceipt(),
                String.valueOf(accountsReceivable.getStatus()),
                accountsReceivable.getCategory(),
                accountsReceivable.getUser().getId()
        );
    }

}
