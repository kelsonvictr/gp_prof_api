package br.com.gerenciador.api.controller;

// Importação dos DTOs (Data Transfer Objects), usados para transferir dados entre cliente e servidor
import br.com.gerenciador.api.dto.FornecedorRequestDTO;
import br.com.gerenciador.api.dto.FornecedorResponseDTO;

// Importação do serviço que contém a lógica de negócio
import br.com.gerenciador.api.service.FornecedorService;

// Importação para validação dos dados recebidos no corpo das requisições
import jakarta.validation.Valid;

// Lombok: gera automaticamente um construtor com os atributos marcados como `final`
import lombok.RequiredArgsConstructor;

// Importação de classes do Spring para manipular requisições HTTP
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe responsável por expor os endpoints da API relacionados aos fornecedores.
 * Aqui definimos as rotas e como elas interagem com a camada de serviço.
 */
@RestController // Indica que essa classe é um controlador REST (expondo endpoints da API)
@RequestMapping("/fornecedores") // Define o caminho base dos endpoints (exemplo: /fornecedores)
@RequiredArgsConstructor // Lombok gera automaticamente um construtor para inicializar os atributos `final`
public class FornecedorController {

    /**
     * Injeção de dependência via **Construtor Implícito (Constructor Injection)**
     *
     * - Como usamos `@RequiredArgsConstructor`, o Lombok gera automaticamente um construtor para inicializar
     *   o atributo `fornecedorService`, pois ele está declarado como `final`.
     * - Esse tipo de injeção de dependência é **a mais recomendada** no Spring, pois:
     *   ✅ Garante que a dependência seja obrigatória no momento da criação do objeto.
     *   ✅ Facilita testes unitários, pois permite injetar mocks.
     *   ✅ Evita a necessidade de usar `@Autowired`, que era mais comum em versões antigas do Spring.
     */
    private final FornecedorService fornecedorService;

    /**
     * Endpoint para criar um novo fornecedor.
     *
     * @param dto Dados do fornecedor enviados no corpo da requisição
     * @return FornecedorResponseDTO contendo os dados do fornecedor criado
     */
    @PostMapping // Mapeia requisições HTTP POST para este método
    public ResponseEntity<FornecedorResponseDTO> criarFornecedor(@Valid @RequestBody FornecedorRequestDTO dto) {
        // `@Valid` força a validação do DTO antes de processar a requisição
        // `@RequestBody` indica que os dados vêm no corpo da requisição

        /**
         * 📌 O que é ResponseEntity e por que usá-lo?
         *
         * ResponseEntity é um tipo de retorno que permite personalizar completamente a resposta HTTP.
         *
         * ✅ Permite definir status HTTP customizados (200, 201, 400, etc.)
         * ✅ Permite adicionar cabeçalhos HTTP personalizados
         * ✅ Melhor controle sobre a resposta para o cliente
         *
         * 🔍 Melhorando este endpoint:
         * Atualmente, ele retorna `ResponseEntity.ok(...)`, que equivale a um status HTTP **200 OK**.
         * No entanto, para criação de recursos, o status mais adequado seria **201 Created**.
         *
         * 📌 Vamos melhorar para seguir a melhor prática:
         */
        FornecedorResponseDTO fornecedorCriado = fornecedorService.criarFornecedor(dto);
        return ResponseEntity.status(201).body(fornecedorCriado); // Retorna 201 Created
    }

    /**
     * Endpoint para listar todos os fornecedores.
     *
     * @return Lista de fornecedores cadastrados
     */
    @GetMapping // Mapeia requisições HTTP GET para este método
    public ResponseEntity<List<FornecedorResponseDTO>> listarTodosFornecedores() {
        return ResponseEntity.ok(fornecedorService.listarTodosFornecedores());
    }

    /**
     * Endpoint para buscar um fornecedor pelo ID.
     *
     * @param id Identificador do fornecedor (extraído da URL)
     * @return Dados do fornecedor correspondente
     */
    @GetMapping("/{id}") // Define um parâmetro dinâmico na URL (exemplo: /fornecedores/5)
    public ResponseEntity<FornecedorResponseDTO> buscarFornecedorPeloId(@PathVariable Long id) {
        // `@PathVariable` extrai o ID da URL e o passa como argumento para o método
        return ResponseEntity.ok(fornecedorService.buscarFornecedorPeloId(id));
    }

    /**
     * Endpoint para atualizar os dados de um fornecedor.
     *
     * @param id  ID do fornecedor a ser atualizado (extraído da URL)
     * @param dto Novos dados do fornecedor (vindos no corpo da requisição)
     * @return Fornecedor atualizado
     */
    @PutMapping("/{id}") // Mapeia requisições HTTP PUT para atualização de recursos
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FornecedorResponseDTO> atualizarFornecedorPeloId(
            @PathVariable Long id, @Valid @RequestBody FornecedorRequestDTO dto) {
        return ResponseEntity.ok(fornecedorService.atualizarFornecedorPeloId(id, dto));
    }

    /**
     * Endpoint para deletar um fornecedor pelo ID.
     *
     * @param id Identificador do fornecedor a ser excluído
     * @return Sem conteúdo (204 No Content)
     */
    @DeleteMapping("/{id}") // Mapeia requisições HTTP DELETE
    public ResponseEntity<Void> deletarFornecedorPeloId(@PathVariable Long id) {
        fornecedorService.deletarFornecedorPeloId(id);
        return ResponseEntity.noContent().build(); // Retorna um HTTP 204 (No Content)
    }
}
