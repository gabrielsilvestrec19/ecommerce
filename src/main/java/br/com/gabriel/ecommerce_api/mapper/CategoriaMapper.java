package br.com.gabriel.ecommerce_api.mapper;

import br.com.gabriel.ecommerce_api.dto.CategoriaRequestDTO;
import br.com.gabriel.ecommerce_api.dto.CategoriaResponseDTO;
import br.com.gabriel.ecommerce_api.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaResponseDTO toDTO(Categoria categoria){
        if (categoria == null){
            return null;
        }
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
    }

    public Categoria toEntity(CategoriaRequestDTO dto){
        if(dto == null){
            return null;
        }
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        return categoria;
    }
}
