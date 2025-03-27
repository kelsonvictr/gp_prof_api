package br.com.gerenciador.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 📌 Representa a entidade `Endereco` no banco de dados.
 *
 * 🚀 **O que é uma entidade JPA?**
 * ✅ Uma classe Java que representa uma tabela no banco de dados.
 * ✅ O JPA (Java Persistence API) mapeia essa classe para uma tabela SQL automaticamente.
 * ✅ Cada atributo desta classe será uma **coluna** na tabela `endereco`.
 * ✅ O Spring Boot, junto com o Hibernate (implementação do JPA), gerencia essa entidade.
 */
@Entity // Indica que esta classe é uma entidade JPA e será mapeada para uma tabela no banco de dados
@Table(name = "endereco") // Define o nome da tabela no banco de dados
@NoArgsConstructor // Gera automaticamente um construtor vazio (necessário para o JPA)
@AllArgsConstructor // Gera automaticamente um construtor com todos os atributos
@Data // Lombok: gera automaticamente `getters`, `setters`, `toString`, `equals` e `hashCode`
public class Endereco implements Serializable { // Serializable permite que o objeto seja convertido em bytes (necessário para algumas operações, como cache e comunicação entre sistemas)

    /**
     * 📌 Chave primária da tabela `endereco`.
     *
     * 🛑 `@Id`: Indica que este campo é a chave primária da entidade.
     * 🛑 `@GeneratedValue(strategy = GenerationType.IDENTITY)`:
     *    - Garante que o banco de dados **gere automaticamente o ID** ao inserir um novo endereço.
     *    - `IDENTITY` significa que o ID é gerado pelo próprio banco e não pelo Hibernate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 📌 Logradouro (rua, avenida, etc.).
     * 🔹 `@Column(name = "logradouro")`: Define o nome da coluna no banco.
     * 🔹 `nullable = false`: O campo **não pode ser nulo**.
     * 🔹 `length = 150`: Define o **tamanho máximo** de 150 caracteres.
     */
    @Column(name = "logradouro", nullable = false, length = 150)
    private String logradouro;

    /**
     * 📌 Número do endereço.
     * 🔹 `nullable = false`: O número **é obrigatório**.
     * 🔹 `length = 10`: Permite valores curtos como "12A", "4500", "S/N".
     */
    @Column(name = "numero", nullable = false, length = 10)
    private String numero;

    /**
     * 📌 Complemento (opcional).
     * 🔹 `length = 50`: Máximo de 50 caracteres.
     * 🔹 **Não usamos `nullable = false`**, pois o complemento é opcional.
     */
    @Column(name = "complemento", length = 50)
    private String complemento;

    /**
     * 📌 Bairro.
     * 🔹 Obrigatório (`nullable = false`).
     * 🔹 Limitado a 50 caracteres (`length = 50`).
     */
    @Column(name = "bairro", nullable = false, length = 50)
    private String bairro;

    /**
     * 📌 Cidade.
     * 🔹 Obrigatório (`nullable = false`).
     * 🔹 Limitado a 50 caracteres.
     */
    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;

    /**
     * 📌 Estado.
     * 🔹 Obrigatório (`nullable = false`).
     * 🔹 Limitado a 50 caracteres.
     */
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    /**
     * 📌 País.
     * 🔹 Obrigatório (`nullable = false`).
     * 🔹 Limitado a 50 caracteres.
     */
    @Column(name = "pais", nullable = false, length = 50)
    private String pais;

    /**
     * 📌 Código Postal (CEP).
     * 🔹 Obrigatório (`nullable = false`).
     * 🔹 Limitado a 20 caracteres (`length = 20`), pois alguns países possuem CEPs longos.
     */
    @Column(name = "cep", nullable = false, length = 20)
    private String cep;

}
