package br.com.gabriel.ecommerce_api.controller;

import br.com.gabriel.ecommerce_api.dto.PedidoRequestDTO;
import br.com.gabriel.ecommerce_api.dto.PedidoResponseDTO;
import br.com.gabriel.ecommerce_api.enums.StatusPedido;
import br.com.gabriel.ecommerce_api.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> salva(@RequestBody PedidoRequestDTO dto){
        PedidoResponseDTO ped = pedidoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ped);
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> autualizaStatus(@PathVariable Long id,
                                                              @RequestParam StatusPedido status){
        PedidoResponseDTO ped = pedidoService.atualizarStatus(id, status);
        return ResponseEntity.ok(ped);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<PedidoResponseDTO> autualizaPedido(@PathVariable Long id,
                                                              @RequestBody PedidoRequestDTO dto){
        PedidoResponseDTO ped = pedidoService.atualizar(id, dto);
        return ResponseEntity.ok(ped);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscaPorId(@PathVariable Long id){
        PedidoResponseDTO ped = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(ped);
    }
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listaTodos(){
        List<PedidoResponseDTO> peds = pedidoService.listarTodos();
        return ResponseEntity.ok(peds);
    }
    @GetMapping("/clienteid/{id}")
    public ResponseEntity<List<PedidoResponseDTO>> listaPorIdCliente(@PathVariable Long id){
        List<PedidoResponseDTO> peds = pedidoService.listarPorIdCliente(id);
        return ResponseEntity.ok(peds);
    }
    @GetMapping("/clientenome")
    public  ResponseEntity<List<PedidoResponseDTO>> listaPorNomeCliente(@RequestParam String nome){
        List<PedidoResponseDTO> peds = pedidoService.listarPorNomeCliente(nome);
        return  ResponseEntity.ok(peds);
    }
    @GetMapping("/status/{status}")
    public  ResponseEntity<List<PedidoResponseDTO>> listaPorStatus(@PathVariable StatusPedido status){
        List<PedidoResponseDTO> peds = pedidoService.listarPorStatus(status);
        return ResponseEntity.ok(peds);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta(@PathVariable Long id){
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
