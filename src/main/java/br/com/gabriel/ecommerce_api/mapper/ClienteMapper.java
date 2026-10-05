package br.com.gabriel.ecommerce_api.mapper;

import br.com.gabriel.ecommerce_api.dto.ClienteRequestDTO;
import br.com.gabriel.ecommerce_api.dto.ClienteResponseDTO;
import br.com.gabriel.ecommerce_api.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public ClienteResponseDTO toDTO (Cliente cliente){
        if (cliente == null){
            return null;
        }
        return new ClienteResponseDTO(cliente.getId(), cliente.getEmail(), cliente.getNome());
    }

    public Cliente toEntity (ClienteRequestDTO dto){
        if (dto == null){
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setCpf(dto.cpf());
        return cliente;
    }

}
