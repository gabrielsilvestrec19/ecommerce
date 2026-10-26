package br.com.gabriel.ecommerce_api.service.impl;

import br.com.gabriel.ecommerce_api.dto.ClienteRequestDTO;
import br.com.gabriel.ecommerce_api.dto.ClienteResponseDTO;
import br.com.gabriel.ecommerce_api.entity.Cliente;
import br.com.gabriel.ecommerce_api.mapper.ClienteMapper;
import br.com.gabriel.ecommerce_api.repository.ClienteRepository;
import br.com.gabriel.ecommerce_api.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarPorId(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("O cliente de id: " + id + " não existe.");
        }
        return clienteMapper.toDTO(clienteOptional.get());
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarPorEmail(String email) {
        Optional<Cliente> clienteOptional = clienteRepository.findByEmail(email);
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("O cliente com o e-mail: " + email + " não existe.");
        }
        return clienteMapper.toDTO(clienteOptional.get());
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarPorCPF(String cpf) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("O cliente com o CPF: " + cpf + " não existe.");
        }
        return clienteMapper.toDTO(clienteOptional.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponseDTO> clientesDto = new ArrayList<>();
        for (Cliente cli : clientes) {
            clientesDto.add(clienteMapper.toDTO(cli));
        }
        return clientesDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> buscarPeloNome(String nome) {
        List<Cliente> clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);
        List<ClienteResponseDTO> clientesDto = new ArrayList<>();
        for (Cliente cli : clientes) {
            clientesDto.add(clienteMapper.toDTO(cli));
        }
        return clientesDto;
    }

    @Override
    @Transactional
    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteMapper.toDTO(clienteSalvo);
    }

    @Override
    @Transactional
    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("O cliente de id: " + id + " não existe.");
        }
        Cliente cliente = clienteMapper.toEntity(dto);
        cliente.setId(id);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteMapper.toDTO(clienteSalvo);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("O cliente de id: " + id + " não existe.");
        }
        clienteRepository.delete(clienteOptional.get());
    }
}