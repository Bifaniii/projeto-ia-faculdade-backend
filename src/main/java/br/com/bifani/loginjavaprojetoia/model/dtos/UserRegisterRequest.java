package br.com.bifani.loginjavaprojetoia.model.dtos;

public record UserRegisterRequest(
        String name,
        String email,
        String password
) {
}
