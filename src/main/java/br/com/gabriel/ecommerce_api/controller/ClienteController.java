package br.com.gabriel.ecommerce_api.controller;

import br.com.gabriel.ecommerce_api.dto.ClienteRequestDTO;
import br.com.gabriel.ecommerce_api.dto.ClienteResponseDTO;
import br.com.gabriel.ecommerce_api.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO clienteSalvo = clienteService.salvar(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscaPorId(@PathVariable Long id){
        ClienteResponseDTO cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponseDTO>> buscaPorNome(@RequestParam String nome){
        List<ClienteResponseDTO> cliente = clienteService.buscarPeloNome(nome);
        return ResponseEntity.ok(cliente);
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDTO> buscaPorCpf(@PathVariable String cpf){
        ClienteResponseDTO cliente = clienteService.buscarPorCPF(cpf);
        return ResponseEntity.ok(cliente);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteResponseDTO> buscaPorEmail(@PathVariable String email){
        ClienteResponseDTO cliente = clienteService.buscarPorEmail(email);
        return ResponseEntity.ok(cliente);
    }
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listaTodos(){
        List<ClienteResponseDTO> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualiza(@PathVariable Long id ,@RequestBody ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO clienteResponseDTO = clienteService.atualizar(id, clienteRequestDTO);
        return ResponseEntity.ok(clienteResponseDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPorId(@PathVariable Long id){
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
