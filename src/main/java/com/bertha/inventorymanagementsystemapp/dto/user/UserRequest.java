package com.bertha.inventorymanagementsystemapp.dto.user;

public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
