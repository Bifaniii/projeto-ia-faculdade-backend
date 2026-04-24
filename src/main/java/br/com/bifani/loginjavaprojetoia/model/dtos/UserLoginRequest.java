package br.com.bifani.loginjavaprojetoia.model.dtos;

public record UserLoginRequest(
        String email,
        String password
) {
}
