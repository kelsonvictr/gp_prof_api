package br.com.gerenciador.api.mapper;

import br.com.gerenciador.api.dto.FornecedorRequestDTO;
import br.com.gerenciador.api.dto.FornecedorResponseDTO;
import br.com.gerenciador.api.model.Fornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 📌 Interface responsável por converter entre `FornecedorRequestDTO`, `FornecedorResponseDTO` e `Fornecedor` (entidade).
 *
 * 🚀 **O que é um Mapper e por que usá-lo?**
 * ✅ Um **Mapper** converte objetos de um tipo para outro sem precisar escrever código manualmente.
 * ✅ Usamos a biblioteca **MapStruct**, que gera automaticamente a implementação dos métodos.
 * ✅ Facilita a **manutenção do código**, pois evita repetição de `getters` e `setters` para conversão de objetos.
 * ✅ **Aumenta a performance**, pois as conversões são geradas **em tempo de compilação** e não em tempo de execução.
 *
 * 🎯 **Objetivo deste Mapper:**
 * 🔄 Converter um `FornecedorRequestDTO` (entrada da API) para um `Fornecedor` (entidade do banco).
 * 🔄 Converter um `Fornecedor` para um `FornecedorResponseDTO` (retorno da API).
 */
@Mapper(componentModel = "spring") // Diz ao Spring para gerenciar esse Mapper como um Bean
public interface FornecedorMapper {

    /**
     * 📌 Converte um `FornecedorRequestDTO` para uma entidade `Fornecedor`.
     *
     * 🛑 `@Mapping(target = "id", ignore = true)`:
     * - Garante que o campo `id` da entidade **não seja mapeado**, pois será gerado automaticamente pelo banco de dados.
     * - Isso evita que um ID inválido seja setado ao criar um novo fornecedor.
     *
     * 🔄 Esse método é usado quando recebemos um fornecedor via API e precisamos persistir no banco.
     */
    @Mapping(target = "id", ignore = true)
    Fornecedor toEntity(FornecedorRequestDTO dto);

    /**
     * 📌 Converte uma entidade `Fornecedor` para um `FornecedorResponseDTO`.
     *
     * 🔄 Esse método é usado quando pegamos dados do banco e queremos devolver como resposta na API.
     * ✅ Ele mantém apenas os dados necessários para resposta, sem expor detalhes internos da entidade.
     */
    FornecedorResponseDTO toDTO(Fornecedor fornecedor);
}
