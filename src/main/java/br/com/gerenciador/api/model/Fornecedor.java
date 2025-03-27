package br.com.gerenciador.api.model;

import br.com.gerenciador.api.enums.TipoFornecedorEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * 📌 Representa a entidade `Fornecedor` no banco de dados.
 *
 * 🚀 **O que é uma entidade JPA?**
 * ✅ Uma classe Java que representa uma tabela no banco de dados.
 * ✅ O JPA (Java Persistence API) mapeia essa classe automaticamente para uma tabela SQL.
 * ✅ Cada atributo desta classe será uma **coluna** na tabela `fornecedor`.
 */
@Entity // Indica que esta classe será uma entidade gerenciada pelo JPA
@Table(name = "fornecedor") // Define o nome da tabela no banco
@AllArgsConstructor // Lombok: gera automaticamente um construtor com todos os atributos
@NoArgsConstructor // Lombok: gera automaticamente um construtor vazio (necessário para o JPA)
@Data // Lombok: gera `getters`, `setters`, `toString`, `equals` e `hashCode`
public class Fornecedor {

    /**
     * 📌 Chave primária da tabela `fornecedor`.
     *
     * 🛑 `@Id`: Define que esse campo é a **chave primária**.
     * 🛑 `@GeneratedValue(strategy = GenerationType.IDENTITY)`:
     *    - O banco de dados **gera o ID automaticamente** quando um novo fornecedor é salvo.
     *    - `IDENTITY` significa que o ID será gerado pelo próprio banco (autoincremento).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 📌 Nome do fornecedor.
     * 🔹 `nullable = false`: Campo **obrigatório**.
     * 🔹 `length = 100`: Nome pode ter até **100 caracteres**.
     */
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    /**
     * 📌 CNPJ do fornecedor.
     * 🔹 `nullable = false`: O CNPJ **é obrigatório**.
     * 🔹 `unique = true`: O CNPJ **não pode se repetir** (garante que não haja duplicatas).
     * 🔹 `length = 14`: CNPJ deve ter **exatamente 14 dígitos**.
     * 🔹 `updatable = false`: O CNPJ **não pode ser alterado depois de cadastrado**.
     */
    @Column(name = "cnpj", nullable = false, unique = true, length = 14, updatable = false)
    private String cnpj;

    /**
     * 📌 Tipo do fornecedor (COMUM ou PREMIUM).
     *
     * 🔹 `nullable = false`: O campo **é obrigatório**.
     * 🔹 `@Enumerated(EnumType.STRING)`:
     *    - Armazena o valor do Enum como **texto** no banco (`"COMUM"` ou `"PREMIUM"`).
     *    - Se não usarmos essa anotação, o JPA pode armazenar os valores como números (0,1...), dificultando a leitura.
     */
    @Column(name = "tipo_fornecedor", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoFornecedorEnum tipoFornecedor;

    /**
     * 📌 Relação **One-to-One** (Um fornecedor tem UM endereço).
     *
     * 🛑 `@OneToOne`: Define que um fornecedor **possui apenas um endereço**.
     *
     * 🔹 `cascade = CascadeType.ALL`:
     *    - Garante que qualquer operação (inserção, atualização ou remoção) no fornecedor **afete o endereço automaticamente**.
     *    - Por exemplo, se deletarmos um fornecedor, o endereço dele também será deletado.
     *
     * 🔹 `@JoinColumn(name = "endereco_id", referencedColumnName = "id")`:
     *    - Define a chave estrangeira (`endereco_id`) que relaciona essa tabela com a tabela `endereco`.
     *    - `referencedColumnName = "id"` indica que o campo `id` da tabela `endereco` é a referência.
     */
    @OneToOne(cascade = CascadeType.ALL) // Define a relação 1-para-1 e propaga operações entre as entidades
    @JoinColumn(name = "endereco_id", referencedColumnName = "id") // Define a chave estrangeira
    private Endereco endereco;

    /**
     * 📌 Data de criação do fornecedor.
     *
     * 🔹 `nullable = false`: O campo **é obrigatório**.
     * 🔹 `updatable = false`: A data **não pode ser alterada após a criação**.
     * 🔹 `@CreationTimestamp`: Hibernate insere **automaticamente** a data e hora no momento da criação do registro.
     */
    @Column(name = "criado_em", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime criadoEm;

    /**
     * 📌 Data da última atualização do fornecedor.
     *
     * 🔹 `@UpdateTimestamp`: Hibernate **atualiza automaticamente** esse campo sempre que a entidade for modificada.
     */
    @Column(name = "atualizado_em")
    @UpdateTimestamp
    private LocalDateTime atualizadoEm;
}
