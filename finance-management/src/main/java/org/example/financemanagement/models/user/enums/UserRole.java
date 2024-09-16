package org.example.financemanagement.models.user.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("user");

    private final String role;

    UserRole(String role){
        this.role = role;
    }
}
