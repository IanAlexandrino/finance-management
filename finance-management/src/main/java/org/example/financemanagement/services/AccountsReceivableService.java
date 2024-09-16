package org.example.financemanagement.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.financemanagement.models.accounts_receivable.AccountsReceivable;
import org.example.financemanagement.models.accounts_receivable.dtos.AccountReceivableUpdateDTO;
import org.example.financemanagement.models.accounts_receivable.dtos.AccountsReceivableCreateDTO;
import org.example.financemanagement.models.accounts_receivable.dtos.AccountsReceivableDetailsDTO;
import org.example.financemanagement.models.user.User;
import org.example.financemanagement.repositories.AccountsReceivableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountsReceivableService {

    @Autowired
    private AccountsReceivableRepository accountsReceivableRepository;

    @Autowired
    private UserService userService;

    public AccountsReceivable findById(Long id){
        Optional<AccountsReceivable> accountsReceivable = accountsReceivableRepository.findById(id);
        return  accountsReceivable.orElseThrow(
                () -> new EntityNotFoundException(
                        "Account payable not found! id: " + id
                )
        );
    }

    public List<AccountsReceivableDetailsDTO> findAll(){
        return this.accountsReceivableRepository
                .findAll()
                .stream()
                .map(AccountsReceivableDetailsDTO::new)
                .collect(Collectors.toList());
    }

    public List<AccountsReceivableDetailsDTO> findAllByUser_Id(Long userId){
        return this.accountsReceivableRepository
                .findAllByUser_Id(userId)
                .stream()
                .map(AccountsReceivableDetailsDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public AccountsReceivableDetailsDTO create(AccountsReceivableCreateDTO accountsReceivableCreateDTO){
        User user = this.userService.findById(accountsReceivableCreateDTO.userId());
        AccountsReceivable accountsReceivable = new AccountsReceivable(accountsReceivableCreateDTO, user);
        this.accountsReceivableRepository.save(accountsReceivable);
        return new AccountsReceivableDetailsDTO(accountsReceivable);
    }

    @Transactional
    public AccountsReceivableDetailsDTO update(AccountReceivableUpdateDTO accountReceivableUpdateDTO){
        AccountsReceivable accountsReceivable = this.findById(accountReceivableUpdateDTO.id());
        accountsReceivable.updateAccountsReceivable(accountReceivableUpdateDTO);
        this.accountsReceivableRepository.save(accountsReceivable);
        return new AccountsReceivableDetailsDTO(accountsReceivable);
    }

    @Transactional
    public void delete(Long id){
        this.findById(id);
        try {
            this.accountsReceivableRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Unable to delete because this entity has related objects");
        }
    }
}
