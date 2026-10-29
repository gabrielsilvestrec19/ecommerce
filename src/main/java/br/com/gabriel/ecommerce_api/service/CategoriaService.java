package br.com.gabriel.ecommerce_api.service;

import br.com.gabriel.ecommerce_api.dto.CategoriaRequestDTO;
import br.com.gabriel.ecommerce_api.dto.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponseDTO> listarTodos();
    CategoriaResponseDTO buscarPeloNome(String nome);
    CategoriaResponseDTO buscarPorId(Integer id);
    CategoriaResponseDTO salvar (CategoriaRequestDTO dto);
    CategoriaResponseDTO atualizar (Integer id, CategoriaRequestDTO dto);
    void deletar (Integer id);
    void deletarPeloNome (String nome);
}
