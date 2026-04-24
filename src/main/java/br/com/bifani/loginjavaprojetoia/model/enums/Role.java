package br.com.bifani.loginjavaprojetoia.model.enums;

public enum Role {
    USER("Usuário Padrão"),
    ADMIN("Administrador do Sistema");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
