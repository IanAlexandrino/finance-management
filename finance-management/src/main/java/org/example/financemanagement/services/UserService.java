package org.example.financemanagement.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.financemanagement.models.user.User;
import org.example.financemanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new EntityNotFoundException("User not found! id: " + id));
    }
}
