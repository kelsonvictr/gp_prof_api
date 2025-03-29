package br.com.gerenciador.api.controller;

import br.com.gerenciador.api.dto.EstatisticasResponseDTO;
import br.com.gerenciador.api.repository.ClienteRepository;
import br.com.gerenciador.api.repository.FornecedorRepository;
import br.com.gerenciador.api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estatisticas")
@RequiredArgsConstructor
public class EstatisticasController {

    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<EstatisticasResponseDTO> obterEstatisticas() {
        long totalFornecedores = fornecedorRepository.count();
        long totalProdutos = produtoRepository.count();
        long totalClientes = clienteRepository.count();

        EstatisticasResponseDTO response = new EstatisticasResponseDTO(
                totalFornecedores,
                totalProdutos,
                totalClientes
        );

        return ResponseEntity.ok(response);
    }
}
