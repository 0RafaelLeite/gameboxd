package br.com.rafael.gameboxd.domain.credential;

public record RegisterDTO(String login, String password, UserRole role) {
}
