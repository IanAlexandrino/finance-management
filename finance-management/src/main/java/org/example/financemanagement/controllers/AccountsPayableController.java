package org.example.financemanagement.controllers;

import jakarta.validation.Valid;
import org.example.financemanagement.models.accounts_payable.dtos.AccountsPayableCreateDTO;
import org.example.financemanagement.models.accounts_payable.dtos.AccountsPayableDetailsDTO;
import org.example.financemanagement.models.accounts_payable.dtos.AccountsPayableUpdateDTO;
import org.example.financemanagement.services.AccountsPayableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/accounts-payable")
public class AccountsPayableController {

    @Autowired
    private AccountsPayableService accountsPayableService;

    @GetMapping
    public ResponseEntity<List<AccountsPayableDetailsDTO>> findAll(){
        return ResponseEntity.ok(this.accountsPayableService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountsPayableDetailsDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                new AccountsPayableDetailsDTO(
                        this.accountsPayableService.findById(id)
                )
        );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<AccountsPayableDetailsDTO>> findAllByUser_Id(@PathVariable Long id){
         return ResponseEntity.ok(
                 this.accountsPayableService.findAllByUser_Id(id)
         );
    }

    @Validated
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody AccountsPayableCreateDTO accountsPayableCreateDTO){
        AccountsPayableDetailsDTO accountsPayableDetailsDTO = this.accountsPayableService.create(accountsPayableCreateDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(accountsPayableDetailsDTO.id())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Validated
    @PutMapping
    public ResponseEntity<AccountsPayableDetailsDTO> update(@Valid @RequestBody AccountsPayableUpdateDTO accountsPayableUpdateDTO){
        return ResponseEntity.ok(
                this.accountsPayableService.update(accountsPayableUpdateDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.accountsPayableService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
