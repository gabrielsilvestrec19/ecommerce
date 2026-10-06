package br.com.gabriel.ecommerce_api.service;

import br.com.gabriel.ecommerce_api.dto.CategoriaRequestDTO;
import br.com.gabriel.ecommerce_api.dto.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponseDTO> listarTodos();
    CategoriaResponseDTO buscarPeloNome(String nome);
    CategoriaResponseDTO buscarPorId(Long id);
    CategoriaResponseDTO salvar (CategoriaRequestDTO dto);
    CategoriaResponseDTO atualizar (Long id, CategoriaRequestDTO dto);
    void deletar (Long id);
    void deletarPeloNome (String nome);
}
