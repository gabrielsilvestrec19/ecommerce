package br.com.gabriel.ecommerce_api.repository;

import br.com.gabriel.ecommerce_api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByCpf(String cpf);

}
