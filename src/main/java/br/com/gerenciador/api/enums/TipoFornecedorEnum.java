package br.com.gerenciador.api.enums;

/**
 * 📌 Enumeração que define os tipos de fornecedores disponíveis no sistema.
 *
 * 🚀 **O que é um Enum (Enumeração)?**
 * ✅ Um tipo especial de classe em Java que representa um **conjunto fixo de valores constantes**.
 * ✅ Evita o uso de **strings soltas no código**, reduzindo erros e melhorando a legibilidade.
 * ✅ Facilita a manutenção do código, pois os valores são controlados dentro da enumeração.
 * ✅ Garante que o campo `tipoFornecedor` só aceite valores válidos (`COMUM` ou `PREMIUM`).
 */
public enum TipoFornecedorEnum {

    /**
     * 📌 Fornecedor do tipo COMUM.
     * 🔹 Representa um fornecedor padrão sem benefícios extras.
     */
    COMUM,

    /**
     * 📌 Fornecedor do tipo PREMIUM.
     * 🔹 Representa um fornecedor com privilégios ou benefícios adicionais.
     */
    PREMIUM
}
