package br.com.gerenciador.api.dto;

import br.com.gerenciador.api.enums.TipoFornecedorEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 * 📌 DTO (Data Transfer Object) para representar os dados recebidos ao criar ou atualizar um fornecedor.
 *
 * ✅ Protege a camada de modelo, evitando exposição direta das entidades do banco.
 * ✅ Permite a validação de dados antes de serem processados pela aplicação.
 * ✅ Garante que apenas informações necessárias sejam transferidas.
 *
 * 🚀 Aqui utilizamos um `record`, que é uma forma simplificada de criar objetos imutáveis no Java.
 */
public record FornecedorRequestDTO(

        /**
         * 📌 Nome do fornecedor - campo obrigatório.
         *
         * 🛑 `@NotBlank`: Garante que o nome não seja nulo nem vazio ("").
         * 📏 `@Size(max = 100)`: Limita o tamanho do nome para no máximo 100 caracteres.
         */
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100)
        String nome,

        /**
         * 📌 CNPJ do fornecedor - campo obrigatório e deve ter exatamente 14 dígitos numéricos.
         *
         * 🛑 `@NotBlank`: Não permite valores vazios ou nulos.
         * 🔢 `@Pattern(regexp = "\\d{14}")`: Define um formato específico (apenas números, com exatamente 14 dígitos).
         * 💡 Isso evita erros no banco de dados e no sistema ao validar o formato antes mesmo de salvar.
         */
        @CNPJ
        String cnpj,

        /**
         * 📌 Tipo de fornecedor - obrigatório.
         *
         * 🛑 `@NotNull`: O campo deve ser preenchido (não pode ser `null`).
         * 🏷️ O tipo do fornecedor é um enum (`COMUM` ou `PREMIUM`).
         * 📌 Isso impede que o usuário passe um valor inválido para o tipo.
         */
        @NotNull(message = "Tipo de fornecedor é obrigatório")
        TipoFornecedorEnum tipoFornecedor,

        /**
         * 📌 Endereço do fornecedor - DTO aninhado.
         *
         * ✅ `@Valid`: Indica que o objeto `EnderecoDTO` também deve ser validado.
         * 🔍 Isso garante que os campos do endereço sigam as regras de validação definidas no `EnderecoDTO`.
         */
        @Valid
        EnderecoDTO endereco

) {}
