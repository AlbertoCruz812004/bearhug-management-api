package com.bearhug.presentation.dto.auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "status", "jwt"})
public record AuthResponse(
        String username,
        String message,
        String status,
        Boolean jwt
) {
}
