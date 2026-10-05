package br.com.gabriel.ecommerce_api.mapper;

import br.com.gabriel.ecommerce_api.dto.ProdutoRequestDTO;
import br.com.gabriel.ecommerce_api.dto.ProdutoResponseDTO;
import br.com.gabriel.ecommerce_api.entity.Categoria;
import br.com.gabriel.ecommerce_api.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoResponseDTO toDTO(Produto produto){
        if (produto == null){
            return null;
        }
        return new ProdutoResponseDTO(produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidadeNoEstoque(),
                produto.getCategoria() != null ? produto.getCategoria().getId(): null,
                produto.getCategoria() != null ? produto.getCategoria().getNome(): null
        );
    }
    public Produto toEntity(ProdutoRequestDTO dto, Categoria categoria){
        if (dto == null){
            return null;
        }
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setQuantidadeNoEstoque(dto.quantidadeNoEstoque());

        if (categoria != null){
            produto.setCategoria(categoria);
        }
        return produto;
    }
}
