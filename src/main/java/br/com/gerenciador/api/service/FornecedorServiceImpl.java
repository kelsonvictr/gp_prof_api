package br.com.gerenciador.api.service;

import br.com.gerenciador.api.dto.FornecedorRequestDTO;
import br.com.gerenciador.api.dto.FornecedorResponseDTO;
import br.com.gerenciador.api.mapper.EnderecoMapper;
import br.com.gerenciador.api.mapper.FornecedorMapper;
import br.com.gerenciador.api.model.Fornecedor;
import br.com.gerenciador.api.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * 📌 Implementação do serviço `FornecedorService`.
 *
 * 🚀 **Por que usamos `@Service`?**
 * ✅ Indica que esta classe faz parte da **camada de serviço** da aplicação.
 * ✅ Permite que o Spring gerencie essa classe como um **Bean**, tornando-a injetável em outras partes do código.
 * ✅ Segue a arquitetura de **Separation of Concerns** (separação de responsabilidades), mantendo a lógica de negócios separada do Controller.
 */
@Service // Define essa classe como um Bean de serviço no Spring
@RequiredArgsConstructor // Lombok gera automaticamente um construtor com os atributos `final`
public class FornecedorServiceImpl implements FornecedorService {

    /**
     * 📌 **Injeção de Dependência via Construtor**
     *
     * 🚀 **O que é isso?**
     * ✅ O Spring automaticamente injeta as dependências necessárias nesta classe.
     * ✅ Como os atributos são `final`, o Lombok (com `@RequiredArgsConstructor`) gera um construtor automaticamente.
     * ✅ Essa abordagem é melhor do que `@Autowired` porque:
     *   - **Garante que a dependência seja obrigatória.**
     *   - **Facilita a criação de testes unitários.**
     *   - **Evita código boilerplate** (menos código repetitivo).
     */
    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper fornecedorMapper;
    private final EnderecoMapper enderecoMapper;

    /**
     * 📌 Criação de um novo fornecedor.
     *
     * 🚀 **Fluxo deste método:**
     * 1️⃣ Converte `FornecedorRequestDTO` para a entidade `Fornecedor` usando `FornecedorMapper`.
     * 2️⃣ Salva o fornecedor no banco de dados usando `fornecedorRepository.save()`.
     * 3️⃣ Converte o fornecedor salvo para `FornecedorResponseDTO` para enviar como resposta.
     *
     * 🔥 **Uso de Mappers:**
     * ✅ `fornecedorMapper.toEntity(dto)`: Converte DTO para Entidade (entrada da API para persistência no banco).
     * ✅ `fornecedorMapper.toDTO(...)`: Converte Entidade para DTO (dados do banco para resposta da API).
     */
    @Override
    public FornecedorResponseDTO criarFornecedor(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorMapper.toEntity(dto); // Converte DTO para Entidade
        return fornecedorMapper.toDTO(fornecedorRepository.save(fornecedor)); // Salva e retorna DTO
    }

    /**
     * 📌 Lista todos os fornecedores cadastrados.
     *
     * 🚀 **Fluxo deste método:**
     * 1️⃣ Busca todos os fornecedores com `findAll()`.
     * 2️⃣ Converte cada entidade `Fornecedor` para `FornecedorResponseDTO` usando `fornecedorMapper.toDTO()`.
     * 3️⃣ Retorna a lista convertida.
     */
    @Override
    public List<FornecedorResponseDTO> listarTodosFornecedores() {
        /*
        O Stream no Java é uma API introduzida no Java 8 para trabalhar com operações funcionais em coleções de dados (como listas e conjuntos)
        de forma mais declarativa e eficiente.
         Ele permite manipular coleções de maneira funcional sem precisar usar loops tradicionais (for, while).
         * 1. Chamamos `findAll()` no `fornecedorRepository`, que retorna uma lista de fornecedores (`List<Fornecedor>`).
         * 2. Utilizamos `stream()` para transformar essa lista em um fluxo de dados (`Stream<Fornecedor>`), permitindo operações funcionais.
         * 3. Aplicamos `map(fornecedorMapper::toDTO)`, que percorre cada fornecedor e converte para um `FornecedorResponseDTO` usando o método `toDTO`.
         * 4. O `toList()` coleta os elementos do stream e os transforma novamente em uma lista (`List<FornecedorResponseDTO>`).
         */
        return fornecedorRepository.findAll().stream()
                .map(fornecedorMapper::toDTO) // Converte cada fornecedor para DTO
                .toList(); // Retorna uma lista de DTOs
    }

    /**
     * 📌 Busca um fornecedor pelo ID.
     *
     * 🚀 **Fluxo deste método:**
     * 1️⃣ Usa `findById(id)` para buscar o fornecedor.
     * 2️⃣ Se não encontrar, lança uma `ResponseStatusException` com status `404 NOT FOUND`.
     * 3️⃣ Converte a entidade `Fornecedor` para `FornecedorResponseDTO`.
     */
    @Override
    public FornecedorResponseDTO buscarFornecedorPeloId(Long id) {
        /*
        A sintaxe () -> representa Expressões Lambda, um recurso do Java 8 que permite escrever código mais conciso e funcional.
         * 1. Chamamos `findById(id)` no `fornecedorRepository`, que retorna um `Optional<Fornecedor>`.
         * 2. Usamos `.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado"))`:
         *    - Se o fornecedor for encontrado, retorna o objeto `Fornecedor`.
         *    - Se não for encontrado, lança uma exceção `ResponseStatusException` com status 404 (NOT_FOUND).
         * 3. Convertendo o `Fornecedor` para `FornecedorResponseDTO` usando `fornecedorMapper.toDTO(fornecedor)`.
         */
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado")); // Lança erro se não existir
        return fornecedorMapper.toDTO(fornecedor); // Converte para DTO e retorna
    }

    /**
     * 📌 Atualiza um fornecedor existente.
     *
     * 🚀 **Fluxo deste método:**
     * 1️⃣ Busca o fornecedor pelo ID. Se não existir, lança erro `404 NOT FOUND`.
     * 2️⃣ Atualiza os atributos do fornecedor com os dados do DTO.
     * 3️⃣ Usa `enderecoMapper.toEntity(dto.endereco())` para converter o endereço DTO para entidade.
     * 4️⃣ Converte o fornecedor atualizado para `FornecedorResponseDTO` e retorna.
     *
     * 🔥 **Uso da anotação `@Transactional`:**
     * ✅ Garante que todas as operações no banco sejam feitas dentro de uma transação.
     * ✅ Se qualquer erro ocorrer, o Spring **desfaz todas as mudanças**, garantindo consistência dos dados.
     */
    @Transactional
    @Override
    public FornecedorResponseDTO atualizarFornecedorPeloId(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado"));

        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setTipoFornecedor(dto.tipoFornecedor());
        fornecedor.setEndereco(enderecoMapper.toEntity(dto.endereco())); // Converte DTO de endereço para Entidade

        return fornecedorMapper.toDTO(fornecedor); // Converte para DTO e retorna
    }

    /**
     * 📌 Deleta um fornecedor pelo ID.
     *
     * 🚀 **Fluxo deste método:**
     * 1️⃣ Verifica se o fornecedor existe no banco.
     * 2️⃣ Se não existir, lança erro `404 NOT FOUND`.
     * 3️⃣ Usa `deleteById(id)` para remover o fornecedor.
     *
     * 🔥 **Uso da anotação `@Transactional`:**
     * ✅ Garante que, se houver um erro no meio da execução, nenhuma operação parcial será aplicada.
     */
    @Transactional
    @Override
    public void deletarFornecedorPeloId(Long id) {
        if (!fornecedorRepository.existsById(id)) { // Verifica se o fornecedor existe antes de deletar
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado");
        }
        fornecedorRepository.deleteById(id); // Deleta o fornecedor
    }
}
