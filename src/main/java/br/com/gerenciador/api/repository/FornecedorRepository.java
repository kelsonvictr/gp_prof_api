package br.com.gerenciador.api.repository;

import br.com.gerenciador.api.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 📌 Repositório responsável por acessar os dados da entidade `Fornecedor` no banco de dados.
 *
 * 🚀 **O que é um Repository no Spring Boot?**
 * ✅ É uma **interface** que define métodos para acessar o banco de dados.
 * ✅ O Spring Data JPA gera automaticamente as implementações desses métodos.
 * ✅ Permite operações como **salvar, buscar, atualizar e deletar** registros sem precisar escrever SQL manualmente.
 */
@Repository // Indica que esta interface é um componente do Spring responsável pelo acesso ao banco de dados.
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
