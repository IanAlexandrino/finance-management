package org.example.financemanagement.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.financemanagement.models.accounts_payable.AccountsPayable;
import org.example.financemanagement.models.accounts_payable.dtos.AccountsPayableCreateDTO;
import org.example.financemanagement.models.accounts_payable.dtos.AccountsPayableDetailsDTO;
import org.example.financemanagement.models.accounts_payable.dtos.AccountsPayableUpdateDTO;
import org.example.financemanagement.models.user.User;
import org.example.financemanagement.repositories.AccountsPayableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountsPayableService {

    @Autowired
    private AccountsPayableRepository accountsPayableRepository;

    @Autowired
    private UserService userService;

    public AccountsPayable findById(Long id){
        Optional<AccountsPayable> accountsPayable = accountsPayableRepository.findById(id);
        return  accountsPayable.orElseThrow(
                () -> new EntityNotFoundException(
                        "Account payable not found! id: " + id
                )
        );
    }

    public List<AccountsPayableDetailsDTO> findAll(){
        return this.accountsPayableRepository
                .findAll()
                .stream()
                .map(AccountsPayableDetailsDTO::new)
                .collect(Collectors.toList());
    }

    public List<AccountsPayableDetailsDTO> findAllByUser_Id(Long userId){
        return this.accountsPayableRepository
                .findAllByUser_Id(userId)
                .stream()
                .map(AccountsPayableDetailsDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public AccountsPayableDetailsDTO create(AccountsPayableCreateDTO accountsPayableCreateDTO){
        User user = this.userService.findById(accountsPayableCreateDTO.userId());
        AccountsPayable accountsPayable = new AccountsPayable(accountsPayableCreateDTO, user);
        this.accountsPayableRepository.save(accountsPayable);
        return new AccountsPayableDetailsDTO(accountsPayable);
    }

    @Transactional
    public AccountsPayableDetailsDTO update(AccountsPayableUpdateDTO accountsPayableUpdateDTO){
        AccountsPayable accountsPayable = this.findById(accountsPayableUpdateDTO.id());
        accountsPayable.updateAccountsPayable(accountsPayableUpdateDTO);
        this.accountsPayableRepository.save(accountsPayable);
        return new AccountsPayableDetailsDTO(accountsPayable);
    }

    @Transactional
    public void delete(Long id){
        this.findById(id);
        try {
            this.accountsPayableRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Unable to delete because this entity has related objects");
        }
    }
}
