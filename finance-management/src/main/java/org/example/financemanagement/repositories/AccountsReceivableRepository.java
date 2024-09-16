package org.example.financemanagement.repositories;

import org.example.financemanagement.models.accounts_receivable.AccountsReceivable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsReceivableRepository extends JpaRepository<AccountsReceivable, Long> {

    // Query to bring all accounts receivable by user ID
    List<AccountsReceivable> findAllByUser_Id(Long userId);
}
