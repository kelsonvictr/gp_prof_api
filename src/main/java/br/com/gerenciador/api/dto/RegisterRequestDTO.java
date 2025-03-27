package br.com.gerenciador.api.dto;

import br.com.gerenciador.api.enums.Role;

public record RegisterRequestDTO(
        String username,
        String email,
        String senha,
        Role role
) {}
