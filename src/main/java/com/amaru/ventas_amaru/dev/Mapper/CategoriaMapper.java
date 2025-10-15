package com.amaru.ventas_amaru.dev.Mapper;

import com.amaru.ventas_amaru.dev.DTO.CategoriaDTO.CategoriaDTO;
import com.amaru.ventas_amaru.dev.DTO.CategoriaDTO.CategoriaRequestDTO;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Categoria;

public class CategoriaMapper {

    public static Categoria CatToEntity (CategoriaDTO catDto){
        Categoria  cat = new Categoria();
        catDto.setIdCategoria(cat.getIdCategoria());
        catDto.setNombre(cat.getNombre());
        catDto.setIdCategoria(cat.getIdCategoria());
        return cat;
    }

    public static Categoria catRequest (CategoriaRequestDTO catRequest){
        Categoria cat = new Categoria();
        cat.setIdCategoria(catRequest.getIdCategoria());
        cat.setNombre(catRequest.getNombre());
        cat.setActivo(true);
        return cat;
    }

    public static CategoriaDTO catToDTO (Categoria cat){
        CategoriaDTO dto = new CategoriaDTO();
        dto.setIdCategoria(cat.getIdCategoria());
        dto.setNombre(cat.getNombre());
        dto.setActivo(cat.getActivo());
        return dto;
    }
}
