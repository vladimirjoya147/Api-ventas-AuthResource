package com.amaru.ventas_amaru.dev.Implement;

import com.amaru.ventas_amaru.dev.DTO.Usuario.UsuarioRequest;
import com.amaru.ventas_amaru.dev.DTO.VentaDTO.*;
import com.amaru.ventas_amaru.dev.Entity.Cliente;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.MovimientoInventario;
import com.amaru.ventas_amaru.dev.Entity.VentaEntity.DetalleVenta;
import com.amaru.ventas_amaru.dev.Entity.ProductoEntity.Producto;
import com.amaru.ventas_amaru.dev.Entity.VentaEntity.Venta;
import com.amaru.ventas_amaru.dev.Enum.TipoMovimiento;
import com.amaru.ventas_amaru.dev.Feign.UsuariosFeignClient;
import com.amaru.ventas_amaru.dev.Mapper.VentaMapper;
import com.amaru.ventas_amaru.dev.Rabbit.MessageProducer;
import com.amaru.ventas_amaru.dev.Repository.*;
import com.amaru.ventas_amaru.dev.Service.VentaService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static com.amaru.ventas_amaru.dev.Mapper.VentaDetalle.listarVentasMap;

@Service
public class VentaServiceImplement implements VentaService {

    private final VentaRepository ventaRepository;
    private final VentaDetalleRepository ventaDetalleRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;
    private final MovimientoRepository movimientoRepository;
    private final UsuariosFeignClient usuariosFeignClient;
    private final MessageProducer messageProducer;

    public VentaServiceImplement(VentaRepository ventaRepository, VentaDetalleRepository ventaDetalleRepository, ProductoRepository productoRepository, ClienteRepository clienteRepository, MovimientoRepository movimientoRepository, UsuariosFeignClient usuariosFeignClient, MessageProducer messageProducer) {
        this.ventaRepository = ventaRepository;
        this.ventaDetalleRepository = ventaDetalleRepository;
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
        this.movimientoRepository = movimientoRepository;
        this.usuariosFeignClient = usuariosFeignClient;
        this.messageProducer = messageProducer;
    }


    @Override
    public List<VentaListaDTO> listarVentas() {

        List<Venta> ventas = ventaRepository.findAll();

        Set<Long> idsUsuarios = ventas.stream()
                .map(Venta::getUsuarioId)
                .collect(Collectors.toSet());

        List<UsuarioRequest> usuarios = usuariosFeignClient.obtenerUsuariosPorId(idsUsuarios);

        Map<Long, UsuarioRequest> usuariosMap = usuarios.stream()
                .collect(Collectors.toMap(UsuarioRequest::getId, u -> u));

        return ventas.stream()
                .map(v -> {
                    UsuarioRequest usuario = usuariosMap.get(v.getUsuarioId());
                    if (usuario == null) {
                        usuario = new UsuarioRequest();
                        usuario.setNombreCompleto("Usuario desconocido");
                    }
                    return VentaMapper.toDTO(v, usuario);
                })
                .toList();

    }

    @Override
    @Transactional
    public VentaResponse guardarVenta(VentaRequestDTO request) {

        if (request.getDetalles() == null || request.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La lista no puede estar vacía");
        }

        Cliente cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con el id " + request.getIdCliente()));


        UsuarioRequest usuario = usuariosFeignClient.obtenerUsuarioPorId(request.getIdUsuario());
        System.out.printf("Usuario encontrado: %s\n", usuario.getNombreCompleto());
        System.out.println("Usuario encontrado: " + usuario.getId());;



        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setUsuarioId(usuario.getId());
        venta.setFechaVenta(LocalDateTime.now());
        venta.setMetodoPago(Venta.MetodoPago.valueOf(request.getMetodoPago()));
        venta.setTotalVenta(BigDecimal.ZERO);

        Venta ventaGuardada = ventaRepository.save(venta);

        BigDecimal ventatotal=BigDecimal.ZERO;

        for (DetalleVentaRequestDTO d : request.getDetalles()) {
            Producto producto = productoRepository.findById(d.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + d.getIdProducto()));

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(ventaGuardada);
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioUnitario(d.getPrecioUnitario());
            detalle.setDescuento(d.getDescuento());
            ventaDetalleRepository.save(detalle);

            BigDecimal subtotal = d.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(d.getCantidad()))
                    .subtract(d.getDescuento() != null ? d.getDescuento() : BigDecimal.ZERO);

            ventatotal = ventatotal.add(subtotal);

            int stockAnterior = producto.getStock();
            int stockNuevo = stockAnterior - detalle.getCantidad();
            if (stockNuevo < 0) {
                throw new IllegalStateException("No hay stock suficiente para el producto: " + producto.getNombre());
            }

            MovimientoInventario movimiento = new MovimientoInventario();
            movimiento.setProducto(producto);
            movimiento.setTipoMovimiento(TipoMovimiento.VENTA);
            movimiento.setStockAnterior(stockAnterior);
            movimiento.setCantidad(detalle.getCantidad());
            movimiento.setStockNuevo(stockNuevo);

            movimiento.setUsuarioId(usuario.getId());
            //venta.setUsuarioId(usuario.getId());
            movimiento.setReferenciaId(ventaGuardada.getIdVenta());
            movimientoRepository.save(movimiento);

            producto.setStock(stockNuevo);
            productoRepository.save(producto);

        }
        ventaGuardada.setTotalVenta(ventatotal);
        ventaRepository.save(ventaGuardada);

        messageProducer.enviarEventoVenta(
                ventatotal,
                cliente.getNombreCliente(),
                ventaGuardada.getFechaVenta().toString()
        );
        return new VentaResponse(
                venta.getIdVenta(),
                venta.getFechaVenta(),
                venta.getTotalVenta(),
                "Venta registrada con éxito"
        );
    }


    @Override
    public VentaDetallesDTO obtenerVentaPorId(Integer idVenta) {
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con el id " + idVenta));


        UsuarioRequest usuario = usuariosFeignClient.obtenerUsuarioPorId(venta.getUsuarioId());


        List<DetalleVenta> detalles = ventaDetalleRepository.findByVentaIdVenta(idVenta);
        List<VentaMapDTO>listaDTO = listarVentasMap(detalles);

        return new VentaDetallesDTO(
                venta.getIdVenta(),
                venta.getCliente().getNombreCliente(),
                usuario.getNombreCompleto(),
                venta.getFechaVenta(),
                venta.getTotalVenta(),
                venta.getMetodoPago().name(),
                venta.getEstado().name(),
                listaDTO
        );
    }

    @Override
    public byte[] generarComprobantePDF(Integer id){
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con el id " + id));

        UsuarioRequest usuario = usuariosFeignClient.obtenerUsuarioPorId(venta.getUsuarioId());


        List<DetalleVenta> detalles = ventaDetalleRepository.findByVentaIdVenta(id);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        document.open();

        Font tituloFont = new Font(Font.HELVETICA, 18, Font.BOLD);
        Font subtituloFont = new Font(Font.HELVETICA, 14, Font.NORMAL);

        Paragraph titulo = new Paragraph("HappyDrink Licorería", tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        Paragraph subtitulo = new Paragraph("Comprobante de Venta", subtituloFont);
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        document.add(subtitulo);

        document.add(new Paragraph(" ")); // Espacio
        document.add(new Paragraph("Cliente: " + venta.getCliente().getNombreCliente()));
        document.add(new Paragraph("Vendedor: "+ usuario.getNombreCompleto()));
        document.add(new Paragraph("Fecha: " + venta.getFechaVenta()));
        document.add(new Paragraph(" "));

        PdfPTable table = getPdfPTable(detalles);
        document.add(table);


        document.add(new Paragraph(" "));
        Paragraph totalParrafo = new Paragraph("Total: " + venta.getTotalVenta(), new Font(Font.HELVETICA, 12, Font.BOLD));
        totalParrafo.setAlignment(Element.ALIGN_RIGHT);
        document.add(totalParrafo);

        document.close();
        return baos.toByteArray();
    }

    private static PdfPTable getPdfPTable(List<DetalleVenta> detalles) {
        float[] columnWidths = {1f, 5f, 2f, 2f};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);

        Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font cellFont = new Font(Font.HELVETICA, 11, Font.NORMAL);

        String[] headers = {"Cant.", "Producto", "Precio Unit.", "Subtotal"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
        }

        for (DetalleVenta d : detalles) {
            table.addCell(createCell(String.valueOf(d.getCantidad()), cellFont));
            table.addCell(createCell(d.getProducto().getNombre(), cellFont));
            table.addCell(createCell(d.getPrecioUnitario().toString(), cellFont));

            BigDecimal subtotal = d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad()));
            table.addCell(createCell(subtotal.toString(), cellFont));
        }

        return table;
    }

    private static PdfPCell createCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

}

