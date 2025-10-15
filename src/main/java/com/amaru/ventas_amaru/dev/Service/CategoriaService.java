package com.amaru.ventas_amaru.dev.Service;

import com.amaru.ventas_amaru.dev.DTO.CategoriaDTO.CategoriaDTO;
import com.amaru.ventas_amaru.dev.DTO.CategoriaDTO.CategoriaRequestDTO;

import java.util.List;

public interface CategoriaService {

    public List<CategoriaDTO> listarCat();

    public CategoriaDTO buscarCatPorId(Integer id);

    public void eliminarPorId(Integer id);

    public CategoriaDTO guardarCat (CategoriaRequestDTO categoria);

    public CategoriaDTO desctivar (Integer id);

    public CategoriaDTO actualizar(CategoriaRequestDTO categoriaRequestDTO);
}