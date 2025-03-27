package br.com.gerenciador.api.mapper;

import br.com.gerenciador.api.dto.EnderecoDTO;
import br.com.gerenciador.api.model.Endereco;
import org.mapstruct.Mapper;

/**
 * 📌 Interface de mapeamento entre `EnderecoDTO` e `Endereco` usando MapStruct.
 *
 * 🚀 **O que é um Mapper?**
 * ✅ Um Mapper é responsável por **converter objetos de um tipo para outro**, neste caso, entre `EnderecoDTO` e `Endereco`.
 * ✅ Evita a necessidade de escrever código manualmente para conversões (`getters` e `setters`).
 * ✅ Utiliza a biblioteca **MapStruct**, que gera automaticamente as implementações em tempo de compilação.
 *
 * 🎯 **Objetivo desse mapper:**
 * 🔄 Converter um `EnderecoDTO` (usado na API) para `Endereco` (usado na entidade do banco).
 * 🔄 Converter um `Endereco` do banco para um `EnderecoDTO` (para responder na API).
 */
@Mapper(componentModel = "spring") // Indica que este Mapper será gerenciado pelo Spring como um Bean
public interface EnderecoMapper {

    /**
     * 📌 Converte um `EnderecoDTO` para uma entidade `Endereco`.
     * 🔄 Útil quando recebemos um DTO da API e precisamos salvar no banco.
     */
    Endereco toEntity(EnderecoDTO dto);

    /**
     * 📌 Converte uma entidade `Endereco` para um `EnderecoDTO`.
     * 🔄 Útil quando pegamos dados do banco e queremos devolver como resposta na API.
     */
    EnderecoDTO toDTO(Endereco endereco);
}
