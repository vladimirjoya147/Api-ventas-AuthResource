package com.amaru.ventas_amaru.dev.Implement;

import com.amaru.ventas_amaru.dev.DTO.CategoriaDTO.CategoriaDTO;
import com.amaru.ventas_amaru.dev.DTO.CategoriaDTO.CategoriaRequestDTO;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Categoria;
import com.amaru.ventas_amaru.dev.Mapper.CategoriaMapper;
import com.amaru.ventas_amaru.dev.Repository.CategoriaRepository;
import com.amaru.ventas_amaru.dev.Service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoriaServiceImplement implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImplement(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaDTO> listarCat() {
        return categoriaRepository.findAll().stream()
                .map(CategoriaMapper::catToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO buscarCatPorId(Integer id) {
        if(!categoriaRepository.existsById(id)){
            throw new EntityNotFoundException("No se econtro la categoria "+id);
        };
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Categoria no encontrada con el id "+id));
        return CategoriaMapper.catToDTO(categoria);
    }

    @Override
    public void eliminarPorId(Integer id) {
        if(!categoriaRepository.existsById(id)){
            throw new EntityNotFoundException("No se econtro la categoria "+id);
        };
        categoriaRepository.deleteById(id);
    }

    @Override
    public CategoriaDTO guardarCat(CategoriaRequestDTO categoria) {
        Categoria cat = CategoriaMapper.catRequest(categoria);
        cat.setActivo(true);
        Categoria guardar = categoriaRepository.save(cat);
        return CategoriaMapper.catToDTO(guardar);
    }

    @Override
    public CategoriaDTO desctivar(Integer id) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No se encontro Categoria con el id "+id));
        categoria.setActivo(false);
        Categoria guardar = categoriaRepository.save(categoria);
        return CategoriaMapper.catToDTO(guardar);
    }

    @Override
    public CategoriaDTO actualizar(CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = categoriaRepository.findById(categoriaRequestDTO.getIdCategoria())
                .orElseThrow(()->new EntityNotFoundException("No se encontro Categoria con el id "+categoriaRequestDTO.getIdCategoria()));
        Categoria guardar =CategoriaMapper.catRequest(categoriaRequestDTO);
        return CategoriaMapper.catToDTO(categoriaRepository.save(guardar));
    }

}
