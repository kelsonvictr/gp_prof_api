package br.com.gerenciador.api.dto;

import br.com.gerenciador.api.enums.TipoFornecedorEnum;

/**
 * 📌 DTO (Data Transfer Object) para retornar os dados de um fornecedor.
 *
 * 🚀 **Por que usamos um DTO para resposta?**
 * ✅ **Evita expor a entidade do banco diretamente** (proteção da camada de modelo).
 * ✅ **Permite modificar a estrutura da resposta** sem impactar o banco de dados.
 * ✅ **Melhora a performance e segurança**, retornando apenas os dados necessários.
 *
 * 🎯 **Esse DTO é usado para enviar informações do fornecedor ao cliente da API.**
 */
public record FornecedorResponseDTO(

        /**
         * 📌 ID do fornecedor.
         * 🔹 Representa o identificador único do fornecedor no banco de dados.
         * 🔹 Como esse DTO é apenas para resposta, o ID já deve existir (diferente do `FornecedorRequestDTO`, que não precisa de ID).
         */
        Long id,

        /**
         * 📌 Nome do fornecedor.
         * 🔹 Nome registrado do fornecedor que foi salvo no sistema.
         */
        String nome,

        /**
         * 📌 CNPJ do fornecedor.
         * 🔹 Diferente do `FornecedorRequestDTO`, aqui o CNPJ já está validado e salvo no banco.
         * 🔹 Não precisa de validação, pois estamos apenas retornando um dado existente.
         */
        String cnpj,

        /**
         * 📌 Tipo do fornecedor (ENUM).
         * 🔹 Define se o fornecedor é `COMUM` ou `PREMIUM`.
         * 🔹 Como é um Enum, a resposta sempre será um valor válido (sem risco de receber um tipo inválido).
         */
        TipoFornecedorEnum tipoFornecedor,

        /**
         * 📌 Endereço do fornecedor.
         * 🔹 Esse campo é um outro DTO (`EnderecoDTO`), que encapsula os dados do endereço do fornecedor.
         * 🔹 Permite que o endereço seja retornado de forma estruturada e validada.
         */
        EnderecoDTO endereco

) {}
