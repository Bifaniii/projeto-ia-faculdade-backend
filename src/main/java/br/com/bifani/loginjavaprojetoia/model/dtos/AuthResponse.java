package br.com.bifani.loginjavaprojetoia.model.dtos;

public record AuthResponse(
        String token,
        String message
) {
}

