package org.example.financemanagement.models.accounts_receivable;

import jakarta.persistence.*;
import lombok.*;
import org.example.financemanagement.models.accounts_payable.dtos.AccountsPayableUpdateDTO;
import org.example.financemanagement.models.accounts_receivable.dtos.AccountReceivableUpdateDTO;
import org.example.financemanagement.models.accounts_receivable.dtos.AccountsReceivableCreateDTO;
import org.example.financemanagement.models.accounts_receivable.enums.Status;
import org.example.financemanagement.models.user.User;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts_receivable")
@Entity(name = "accounts_receivable")
@EqualsAndHashCode(of = "id")
public class AccountsReceivable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double value;
    private LocalDate dateOfReceipt;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public AccountsReceivable(AccountsReceivableCreateDTO accountsReceivableCreateDTO, User user){
        this.description = accountsReceivableCreateDTO.description();
        this.value = accountsReceivableCreateDTO.value();
        this.dateOfReceipt = LocalDate.parse(accountsReceivableCreateDTO.dueDate());
        this.status = Status.valueOf(accountsReceivableCreateDTO.status().toUpperCase());
        this.category = accountsReceivableCreateDTO.category();
        this.user = user;
    }

    public void updateAccountsReceivable(AccountReceivableUpdateDTO accountReceivableUpdateDTO){
        this.description = accountReceivableUpdateDTO.description();
        this.value = accountReceivableUpdateDTO.value();
        this.dateOfReceipt = LocalDate.parse(accountReceivableUpdateDTO.dueDate());
        this.status = Status.valueOf(accountReceivableUpdateDTO.status().toUpperCase());
        this.category = accountReceivableUpdateDTO.category();
    }
}
