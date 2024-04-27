package com.bertha.inventorymanagementsystemapp.dto.user;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}

