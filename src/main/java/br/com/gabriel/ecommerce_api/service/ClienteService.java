package br.com.gabriel.ecommerce_api.service;

import br.com.gabriel.ecommerce_api.dto.ClienteRequestDTO;
import br.com.gabriel.ecommerce_api.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteResponseDTO> listarTodos();
    List<ClienteResponseDTO> buscarPeloNome(String nome);
    ClienteResponseDTO buscarPorId(Long id);
    ClienteResponseDTO buscarPorEmail(String email);
    ClienteResponseDTO buscarPorCPF(String cpf);
    ClienteResponseDTO salvar(ClienteRequestDTO dto);
    ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto);
    void deletar(Long id);
}
