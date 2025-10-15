package com.amaru.ventas_amaru.dev.Implement;

import com.amaru.ventas_amaru.dev.DTO.ProveedorDTO.ProvedorRequestDTO;
import com.amaru.ventas_amaru.dev.DTO.ProveedorDTO.ProveedorResponseDTO;
import com.amaru.ventas_amaru.dev.DTO.RolDTO.RolRequestDTO;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Proveedor;
import com.amaru.ventas_amaru.dev.Entity.UsuarioEntity.Rol;
import com.amaru.ventas_amaru.dev.Mapper.ProveedorMapper;
import com.amaru.ventas_amaru.dev.Repository.ProveedorRepository;
import com.amaru.ventas_amaru.dev.Service.ProveedorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.amaru.ventas_amaru.dev.Mapper.RolMapper.rolMap;

@Service
public class ProveedorServiceImplement  implements ProveedorService {
    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImplement(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<ProveedorResponseDTO> listarProveedores() {
        List<Proveedor> listar = proveedorRepository.findAll();
        return listar.stream().map(ProveedorMapper::provToDTO).collect(Collectors.toList());
    }

    @Override
    public ProveedorResponseDTO guardarProveedor(ProvedorRequestDTO provedorRequestDTO) {
        Proveedor proveedor = ProveedorMapper.proDtoToEntity(provedorRequestDTO);
        Proveedor guardar =proveedorRepository.save(proveedor);
        return ProveedorMapper.provToDTO(guardar);
    }

    @Override
    public ProveedorResponseDTO desactivar(Integer id, boolean estado) {
        if (!proveedorRepository.existsById(id)){
            throw new EntityNotFoundException("Proveedor no encontrado id "+id);
        }
        Proveedor proveedor = proveedorRepository.findById(id).orElseThrow(null);
        proveedor.setActivo(estado);
        Proveedor guardar = proveedorRepository.save(proveedor);
        return ProveedorMapper.provToDTO(guardar);
    }

    @Override
    public ProveedorResponseDTO buscaPorID(Integer id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No se encontro provevedor con el id "+id));
        return ProveedorMapper.provToDTO(proveedor);
    }

    @Override
    public ProveedorResponseDTO actualizarProveedor(ProvedorRequestDTO prov) {
        Proveedor proveedor = proveedorRepository.findById(prov.getIdProveedor())
                .orElseThrow(()->new EntityNotFoundException("No se encontro provevedor con el id "+prov.getIdProveedor()));
        Proveedor guardar  =ProveedorMapper.proDtoToEntity(prov);
        return ProveedorMapper.provToDTO( proveedorRepository.save(guardar));
    }
}
