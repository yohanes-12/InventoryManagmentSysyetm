package com.bertha.inventorymanagementsystemapp.security;

public record AuthRequest(
        String email,
        String password
) {
}
