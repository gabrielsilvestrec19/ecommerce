package br.com.gabriel.ecommerce_api.service;

import br.com.gabriel.ecommerce_api.dto.PedidoRequestDTO;
import br.com.gabriel.ecommerce_api.dto.PedidoResponseDTO;
import br.com.gabriel.ecommerce_api.enums.StatusPedido;

import java.util.List;

public interface PedidoService {
    List<PedidoResponseDTO> listarTodos();
    PedidoResponseDTO buscarPorId(Long id);
    List<PedidoResponseDTO> listarPorIdCliente (Long id);
    List<PedidoResponseDTO> listarPorNomeCliente (String nome);
    List<PedidoResponseDTO> listarPorStatus (StatusPedido status);
    PedidoResponseDTO salvar (PedidoRequestDTO dto);
    PedidoResponseDTO atualizarStatus (Long id, StatusPedido status);
    PedidoResponseDTO atualizar (Long id, PedidoRequestDTO dto);
    void deletar(Long id);
}