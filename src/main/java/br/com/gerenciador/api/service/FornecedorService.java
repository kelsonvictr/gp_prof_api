package br.com.gerenciador.api.service;

import br.com.gerenciador.api.dto.FornecedorRequestDTO;
import br.com.gerenciador.api.dto.FornecedorResponseDTO;
import java.util.List;

/**
 * 📌 Interface que define os serviços relacionados à entidade `Fornecedor`.
 *
 * 🚀 **O que é essa arquitetura?**
 * ✅ Essa abordagem segue o padrão **Service Layer (Camada de Serviço)**.
 * ✅ **Separa a lógica de negócios da camada de controle (Controller)**, melhorando a organização.
 * ✅ **Usamos uma `interface` para desacoplar a definição da implementação** (princípio da inversão de dependência - SOLID).
 * ✅ A implementação real dos métodos estará na classe `FornecedorServiceImpl`.
 */
public interface FornecedorService {

    /**
     * 📌 Cria um novo fornecedor no sistema.
     * 🔄 Recebe um `FornecedorRequestDTO` com os dados enviados pela API e retorna um `FornecedorResponseDTO` com os dados processados.
     */
    FornecedorResponseDTO criarFornecedor(FornecedorRequestDTO dto);

    /**
     * 📌 Retorna uma lista de todos os fornecedores cadastrados no sistema.
     */
    List<FornecedorResponseDTO> listarTodosFornecedores();

    /**
     * 📌 Busca um fornecedor pelo ID.
     * 🔄 Retorna um `FornecedorResponseDTO` se o fornecedor existir, ou uma exceção caso contrário.
     */
    FornecedorResponseDTO buscarFornecedorPeloId(Long id);

    /**
     * 📌 Atualiza os dados de um fornecedor existente.
     * 🔄 Recebe o ID do fornecedor e um `FornecedorRequestDTO` com as novas informações.
     */
    FornecedorResponseDTO atualizarFornecedorPeloId(Long id, FornecedorRequestDTO dto);

    /**
     * 📌 Deleta um fornecedor pelo ID.
     */
    void deletarFornecedorPeloId(Long id);
}
