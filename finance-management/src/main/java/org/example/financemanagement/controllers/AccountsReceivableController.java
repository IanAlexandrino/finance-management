package org.example.financemanagement.controllers;

import jakarta.validation.Valid;
import org.example.financemanagement.models.accounts_receivable.dtos.AccountReceivableUpdateDTO;
import org.example.financemanagement.models.accounts_receivable.dtos.AccountsReceivableCreateDTO;
import org.example.financemanagement.models.accounts_receivable.dtos.AccountsReceivableDetailsDTO;
import org.example.financemanagement.services.AccountsReceivableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/accounts-receivable")
public class AccountsReceivableController {

    @Autowired
    private AccountsReceivableService accountsReceivableService;

    @GetMapping
    public ResponseEntity<List<AccountsReceivableDetailsDTO>> findAll(){
        return ResponseEntity.ok(this.accountsReceivableService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountsReceivableDetailsDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                new AccountsReceivableDetailsDTO(
                        this.accountsReceivableService.findById(id)
                )
        );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<AccountsReceivableDetailsDTO>> findAllByUser_Id(@PathVariable Long id){
        return ResponseEntity.ok(
                this.accountsReceivableService.findAllByUser_Id(id)
        );
    }

    @Validated
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody AccountsReceivableCreateDTO accountsReceivableCreateDTO){
        AccountsReceivableDetailsDTO accountsReceivableDetailsDTO = this.accountsReceivableService.create(accountsReceivableCreateDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(accountsReceivableDetailsDTO.id())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Validated
    @PutMapping
    public ResponseEntity<AccountsReceivableDetailsDTO> update(@Valid @RequestBody AccountReceivableUpdateDTO accountReceivableUpdateDTO){
        return ResponseEntity.ok(
                this.accountsReceivableService.update(accountReceivableUpdateDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.accountsReceivableService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
