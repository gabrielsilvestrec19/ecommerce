package br.com.gabriel.ecommerce_api.service;
import br.com.gabriel.ecommerce_api.dto.ProdutoRequestDTO;
import br.com.gabriel.ecommerce_api.dto.ProdutoResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoService {
    ProdutoResponseDTO buscarPorId(Long id);
    List<ProdutoResponseDTO> listarTodos ();
    List<ProdutoResponseDTO> buscarPorNomeCategoria(String nomeCategoria);
    List<ProdutoResponseDTO> buscarPorIdCategoria (Long id);
    List<ProdutoResponseDTO> buscarPorFaixaDePreco (BigDecimal valorMenor, BigDecimal valorMaior);
    List<ProdutoResponseDTO> buscarPorNome (String nome);
    ProdutoResponseDTO salvar (ProdutoRequestDTO dto);
    ProdutoResponseDTO atualizarValor (Long id, BigDecimal valor);
    ProdutoResponseDTO atualizarEstoque (Long id, Integer qtEstoque);
    ProdutoResponseDTO atualizar (Long id, ProdutoRequestDTO dto);
    void deletar (Long id);
}
