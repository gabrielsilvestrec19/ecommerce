package br.com.gabriel.ecommerce_api.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProdutoRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        String nome,
        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal preco,
        @PositiveOrZero(message = "Informar um valor de zero ou maior")
        @NotNull(message = "Informar a quatidade no estoque é obrigatório")
        Integer quantidadeNoEstoque,
        @NotNull(message = "Informar a categoria do produto é obrigatório")
        Long categoriaId

) {
}
