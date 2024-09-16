package org.example.financemanagement.models.accounts_payable;

import jakarta.persistence.*;
import lombok.*;
import org.example.financemanagement.models.accounts_payable.dtos.AccountsPayableCreateDTO;
import org.example.financemanagement.models.accounts_payable.dtos.AccountsPayableUpdateDTO;
import org.example.financemanagement.models.accounts_payable.enums.Status;
import org.example.financemanagement.models.user.User;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts_payable")
@Entity(name = "accounts_payable")
@EqualsAndHashCode(of = "id")
public class AccountsPayable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double value;
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public AccountsPayable(AccountsPayableCreateDTO accountsPayableCreateDTO, User user){
        this.description = accountsPayableCreateDTO.description();
        this.value = accountsPayableCreateDTO.value();
        this.dueDate = LocalDate.parse(accountsPayableCreateDTO.dueDate());
        this.status = Status.valueOf(accountsPayableCreateDTO.status().toUpperCase());
        this.category = accountsPayableCreateDTO.category();
        this.user = user;
    }

    public void updateAccountsPayable(AccountsPayableUpdateDTO accountsPayableUpdateDTO){
        this.description = accountsPayableUpdateDTO.description();
        this.value = accountsPayableUpdateDTO.value();
        this.dueDate = LocalDate.parse(accountsPayableUpdateDTO.dueDate());
        this.status = Status.valueOf(accountsPayableUpdateDTO.status().toUpperCase());
        this.category = accountsPayableUpdateDTO.category();
    }
}
