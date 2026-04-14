package carrito.service;

import carrito.model.DetalleFactura;
import carrito.repository.DetalleFacturaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetalleFacturaService {

    private final DetalleFacturaRepository detalleFacturaRepository;

    public DetalleFacturaService(DetalleFacturaRepository detalleFacturaRepository) {
        this.detalleFacturaRepository = detalleFacturaRepository;
    }

    public List<DetalleFactura> listar() {
        return detalleFacturaRepository.findAll();
    }

    public DetalleFactura guardar(DetalleFactura detalle) {
        return detalleFacturaRepository.save(detalle);
    }

    public DetalleFactura buscarPorId(Long id) {
        return detalleFacturaRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        detalleFacturaRepository.deleteById(id);
    }
}