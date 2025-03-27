package br.com.gerenciador.api.repository;

import br.com.gerenciador.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 📌 Repositório responsável por acessar os dados da entidade `Cliente`.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
