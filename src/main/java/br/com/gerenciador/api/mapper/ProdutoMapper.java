package br.com.gerenciador.api.mapper;

import br.com.gerenciador.api.dto.ProdutoResponseDTO;
import br.com.gerenciador.api.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(target = "fornecedor", ignore = true) // será setado manualmente
    Produto toEntity(br.com.gerenciador.api.dto.ProdutoRequestDTO dto);

    ProdutoResponseDTO toDTO(Produto produto);
}
