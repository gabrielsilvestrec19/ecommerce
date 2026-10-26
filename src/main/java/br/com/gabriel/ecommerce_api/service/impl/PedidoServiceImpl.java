package br.com.gabriel.ecommerce_api.service.impl;
import br.com.gabriel.ecommerce_api.dto.ItemPedidoRequestDTO;
import br.com.gabriel.ecommerce_api.dto.PedidoRequestDTO;
import br.com.gabriel.ecommerce_api.dto.PedidoResponseDTO;
import br.com.gabriel.ecommerce_api.entity.Cliente;
import br.com.gabriel.ecommerce_api.entity.ItemPedido;
import br.com.gabriel.ecommerce_api.entity.Pedido;
import br.com.gabriel.ecommerce_api.entity.Produto;
import br.com.gabriel.ecommerce_api.enums.StatusPedido;
import br.com.gabriel.ecommerce_api.mapper.ItemPedidoMapper;
import br.com.gabriel.ecommerce_api.mapper.PedidoMapper;
import br.com.gabriel.ecommerce_api.repository.ClienteRepository;
import br.com.gabriel.ecommerce_api.repository.ItemPedidoRepository;
import br.com.gabriel.ecommerce_api.repository.PedidoRepository;
import br.com.gabriel.ecommerce_api.repository.ProdutoRepository;
import br.com.gabriel.ecommerce_api.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoMapper itemPedidoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoResponseDTO> pedidosDto = new ArrayList<>();
        for(Pedido ped : pedidos){
            pedidosDto.add(pedidoMapper.toDTO(ped));
        }
        return pedidosDto;
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoResponseDTO buscarPorId(Long id) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isEmpty()){
            throw new RuntimeException("O pedido de id: " + id + " não existe");
        }
        PedidoResponseDTO pedido = pedidoMapper.toDTO(optionalPedido.get());
        return pedido;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarPorIdCliente(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            throw new RuntimeException("O cliente de id: "+ id + " não existe.");
        }
        List<Pedido> pedidosDoCliente = pedidoRepository.findByClienteId(id);
        List<PedidoResponseDTO> pedidoDto = new ArrayList<>();
        for (Pedido pedido : pedidosDoCliente){
            pedidoDto.add(pedidoMapper.toDTO(pedido));
        }
        return pedidoDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarPorNomeCliente(String nome) {
        Optional<Cliente> cliente = clienteRepository.findByNome(nome);
        if (cliente.isEmpty()){
            throw new RuntimeException("O cliente de nome: " + nome + " não existe.");
        }
        List<Pedido> pedidos = pedidoRepository.findByClienteNomeContainingIgnoreCase(nome);
        List<PedidoResponseDTO> pedidoResponseDTOS = new ArrayList<>();
        for (Pedido pedido: pedidos){
            pedidoResponseDTOS.add(pedidoMapper.toDTO(pedido));
        }
        return pedidoResponseDTOS;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarPorStatus(StatusPedido status) {

        List<Pedido> pedidos = pedidoRepository.findAllByStatus(status);
        List<PedidoResponseDTO> pedidoResponseDTOS = new ArrayList<>();
        for (Pedido pedido: pedidos){
            pedidoResponseDTOS.add(pedidoMapper.toDTO(pedido));
        }
        return pedidoResponseDTOS;

    }

    @Override
    @Transactional
    public PedidoResponseDTO salvar(PedidoRequestDTO dto) {

        Optional<Cliente> clienteOptional = clienteRepository.findById(dto.clienteId());
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("O cliente de id: " + dto.clienteId() + " não existe.");
        }
        Cliente cliente = clienteOptional.get();


        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());

        List<ItemPedido> itensPedido = new ArrayList<>();
        BigDecimal valorTotalPedido = BigDecimal.ZERO;


        for (ItemPedidoRequestDTO itemDto : dto.itens()) {
            Optional<Produto> produtoOptional = produtoRepository.findById(itemDto.produtoId());
            if (produtoOptional.isEmpty()) {
                throw new RuntimeException("O produto de id: " + itemDto.produtoId() + " não existe.");
            }
            Produto produto = produtoOptional.get();

            if (produto.getQuantidadeNoEstoque() < itemDto.quantidadeDeItensPedidos()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome() +
                        ". Disponível: " + produto.getQuantidadeNoEstoque() + ", Solicitado: " + itemDto.quantidadeDeItensPedidos());
            }

            produto.setQuantidadeNoEstoque(produto.getQuantidadeNoEstoque() - itemDto.quantidadeDeItensPedidos());
            produtoRepository.save(produto);


            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidadeDeItensPedidos(itemDto.quantidadeDeItensPedidos());
            itemPedido.setPrecoUnitario(produto.getPreco());


            BigDecimal subTotal = produto.getPreco().multiply(BigDecimal.valueOf(itemDto.quantidadeDeItensPedidos()));
            valorTotalPedido = valorTotalPedido.add(subTotal);

            itensPedido.add(itemPedido);
        }

        pedido.setItens(itensPedido);
        pedido.setValorDoPedido(valorTotalPedido);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return pedidoMapper.toDTO(pedidoSalvo);
    }

    @Override
    @Transactional
    public PedidoResponseDTO atualizarStatus(Long id, StatusPedido status) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if(pedidoOptional.isEmpty()){
            throw new RuntimeException("O pedido de id: " + id + " não existe.");
        }

        Pedido pedido = pedidoOptional.get();
        pedido.setStatus(status);
        pedidoRepository.save(pedido);
        return  pedidoMapper.toDTO(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO dto) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isEmpty()){
            throw new RuntimeException("O pedido de id: " + id + " não existe.");
        }
        Pedido pedidoExistente = pedidoOptional.get();

        Optional<Cliente> clienteOptional = clienteRepository.findById(dto.clienteId());
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("O cliente de id: " + dto.clienteId() + " não existe.");
        }
        Cliente cliente = clienteOptional.get();

        for (ItemPedido itemAntigo : pedidoExistente.getItens()) {
            Produto produto = itemAntigo.getProduto();
            produto.setQuantidadeNoEstoque(produto.getQuantidadeNoEstoque() + itemAntigo.getQuantidadeDeItensPedidos());
            produtoRepository.save(produto);
        }

        pedidoExistente.setCliente(cliente);

        List<ItemPedido> novosItensPedido = new ArrayList<>();
        BigDecimal valorTotalPedido = BigDecimal.ZERO;

        for (ItemPedidoRequestDTO itemDto : dto.itens()) {
            Optional<Produto> produtoOptional = produtoRepository.findById(itemDto.produtoId());
            if (produtoOptional.isEmpty()) {
                throw new RuntimeException("O produto de id: " + itemDto.produtoId() + " não existe.");
            }
            Produto produto = produtoOptional.get();

            if (produto.getQuantidadeNoEstoque() < itemDto.quantidadeDeItensPedidos()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome() +
                        ". Disponível: " + produto.getQuantidadeNoEstoque() + ", Solicitado: " + itemDto.quantidadeDeItensPedidos());
            }

            produto.setQuantidadeNoEstoque(produto.getQuantidadeNoEstoque() - itemDto.quantidadeDeItensPedidos());
            produtoRepository.save(produto);


            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedidoExistente);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidadeDeItensPedidos(itemDto.quantidadeDeItensPedidos());
            itemPedido.setPrecoUnitario(produto.getPreco());

            BigDecimal subTotal = produto.getPreco().multiply(BigDecimal.valueOf(itemDto.quantidadeDeItensPedidos()));
            valorTotalPedido = valorTotalPedido.add(subTotal);

            novosItensPedido.add(itemPedido);
        }

        pedidoExistente.getItens().clear();
        pedidoExistente.getItens().addAll(novosItensPedido);
        pedidoExistente.setValorDoPedido(valorTotalPedido);

        Pedido pedidoAtualizado = pedidoRepository.save(pedidoExistente);
        return pedidoMapper.toDTO(pedidoAtualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isEmpty()){
            throw new RuntimeException("O pedido de id: "+ id + " não existe.");
        }
        Pedido pedido = pedidoOptional.get();

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getProduto();
            produto.setQuantidadeNoEstoque(produto.getQuantidadeNoEstoque() + item.getQuantidadeDeItensPedidos());
            produtoRepository.save(produto);
        }

        pedidoRepository.delete(pedido);
    }
}
