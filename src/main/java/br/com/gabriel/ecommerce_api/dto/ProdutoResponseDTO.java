package br.com.gabriel.ecommerce_api.dto;
import java.math.BigDecimal;
public record ProdutoResponseDTO(
        Long id,
        String nome,
        BigDecimal preco,
        Integer quantidadeNoEstoque,
        Long categoriaId,
        String categoriaNome
) {
}