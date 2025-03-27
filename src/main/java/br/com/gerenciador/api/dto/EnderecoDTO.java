package br.com.gerenciador.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 📌 DTO (Data Transfer Object) para representar um endereço.
 *
 * 🚀 **Por que usamos um DTO para endereço?**
 * ✅ **Evita duplicação de código**: Podemos reutilizar esse DTO em diferentes partes da aplicação.
 * ✅ **Facilita a validação dos dados** antes de serem processados.
 * ✅ **Garante que os dados do endereço estejam estruturados corretamente**.
 * ✅ **Melhora a organização do código**, separando a lógica de endereço da entidade principal.
 *
 * 🎯 **Esse DTO é usado dentro de `FornecedorRequestDTO` e `FornecedorResponseDTO`**.
 */
public record EnderecoDTO(

        /**
         * 📌 Logradouro (rua, avenida, etc.).
         *
         * 🛑 `@NotBlank`: Garante que o campo **não seja nulo e nem uma string vazia**.
         * 📏 `@Size(max = 150)`: Limita o tamanho máximo para 150 caracteres.
         */
        @NotBlank(message = "Logradouro é obrigatório")
        @Size(max = 150)
        String logradouro,

        /**
         * 📌 Número do endereço.
         *
         * 🛑 `@NotBlank`: Campo obrigatório.
         * 📏 `@Size(max = 10)`: Permite números curtos, como "12A" ou "4500".
         */
        @NotBlank(message = "Número é obrigatório")
        @Size(max = 10)
        String numero,

        /**
         * 📌 Complemento (opcional).
         *
         * 📏 `@Size(max = 50)`: O complemento pode ter até 50 caracteres.
         * 🟢 **Não usamos `@NotBlank`**, pois o complemento pode ser nulo ou vazio.
         */
        @Size(max = 50)
        String complemento,

        /**
         * 📌 Bairro.
         *
         * 🛑 `@NotBlank`: Obrigatório.
         * 📏 `@Size(max = 50)`: Nome do bairro pode ter no máximo 50 caracteres.
         */
        @NotBlank(message = "Bairro é obrigatório")
        @Size(max = 50)
        String bairro,

        /**
         * 📌 Cidade.
         *
         * 🛑 `@NotBlank`: Obrigatório.
         * 📏 `@Size(max = 50)`: Nome da cidade limitado a 50 caracteres.
         */
        @NotBlank(message = "Cidade é obrigatória")
        @Size(max = 50)
        String cidade,

        /**
         * 📌 Estado.
         *
         * 🛑 `@NotBlank`: Obrigatório.
         * 📏 `@Size(max = 50)`: Nome do estado limitado a 50 caracteres.
         */
        @NotBlank(message = "Estado é obrigatório")
        @Size(max = 50)
        String estado,

        /**
         * 📌 País.
         *
         * 🛑 `@NotBlank`: Obrigatório.
         * 📏 `@Size(max = 50)`: Nome do país limitado a 50 caracteres.
         */
        @NotBlank(message = "País é obrigatório")
        @Size(max = 50)
        String pais,

        /**
         * 📌 Código Postal (CEP).
         *
         * 🛑 `@NotBlank`: Obrigatório.
         * 📏 `@Size(max = 20)`: Garante que o CEP tenha no máximo 20 caracteres.
         *
         * 🔍 **Observação**: Idealmente, poderíamos usar `@Pattern` para validar o formato do CEP,
         * dependendo do país, por exemplo: `\\d{5}-\\d{3}` para CEPs brasileiros.
         */
        @NotBlank(message = "CEP é obrigatório")
        @Size(max = 20)
        String cep

) {}
