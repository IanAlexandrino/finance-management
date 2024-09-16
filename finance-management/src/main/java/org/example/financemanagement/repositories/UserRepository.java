package org.example.financemanagement.repositories;

import org.example.financemanagement.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Query to bring a user by login
    UserDetails findByLogin(String login);
}
