package br.com.gerenciador.api.dto;

/**
 * 📌 DTO para retornar dados do cliente na resposta da API.
 */
public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        EnderecoDTO endereco
) {}
