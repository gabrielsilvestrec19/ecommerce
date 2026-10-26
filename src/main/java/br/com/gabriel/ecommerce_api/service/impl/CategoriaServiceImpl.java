package br.com.gabriel.ecommerce_api.service.impl;

import br.com.gabriel.ecommerce_api.dto.CategoriaRequestDTO;
import br.com.gabriel.ecommerce_api.dto.CategoriaResponseDTO;
import br.com.gabriel.ecommerce_api.entity.Categoria;
import br.com.gabriel.ecommerce_api.mapper.CategoriaMapper;
import br.com.gabriel.ecommerce_api.repository.CategoriaRepository;
import br.com.gabriel.ecommerce_api.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    @Transactional(readOnly = true)
    public CategoriaResponseDTO buscarPorId(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("A categoria de id: " + id + " não existe.");
        }
        return categoriaMapper.toDTO(categoriaOptional.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> listarTodos() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaResponseDTO> categoriasDto = new ArrayList<>();
        for (Categoria cat : categorias) {
            categoriasDto.add(categoriaMapper.toDTO(cat));
        }
        return categoriasDto;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaResponseDTO buscarPeloNome(String nome) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome(nome);
        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("A categoria com o nome: " + nome + " não existe.");
        }
        return categoriaMapper.toDTO(categoriaOptional.get());
    }

    @Override
    @Transactional
    public CategoriaResponseDTO salvar(CategoriaRequestDTO dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoriaSalva);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("A categoria de id: " + id + " não existe.");
        }
        Categoria categoria = categoriaMapper.toEntity(dto);
        categoria.setId(id);
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoriaSalva);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("A categoria de id: " + id + " não existe.");
        }
        categoriaRepository.delete(categoriaOptional.get());
    }

    @Override
    @Transactional
    public void deletarPeloNome(String nome) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome(nome);
        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("A categoria com o nome: " + nome + " não existe.");
        }
        categoriaRepository.delete(categoriaOptional.get());
    }
}
