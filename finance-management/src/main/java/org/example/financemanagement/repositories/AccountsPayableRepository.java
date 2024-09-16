package org.example.financemanagement.repositories;

import org.example.financemanagement.models.accounts_payable.AccountsPayable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsPayableRepository extends JpaRepository<AccountsPayable, Long> {

    // Query to bring all accounts payable by user ID
    List<AccountsPayable> findAllByUser_Id(Long userId);
}
