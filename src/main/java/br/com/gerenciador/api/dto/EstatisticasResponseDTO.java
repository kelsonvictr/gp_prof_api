package br.com.gerenciador.api.dto;

public record EstatisticasResponseDTO(
        long totalFornecedores,
        long totalProdutos,
        long totalClientes
) {}
