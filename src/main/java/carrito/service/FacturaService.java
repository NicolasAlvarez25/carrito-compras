package carrito.service;

import carrito.model.Factura;
import carrito.repository.FacturaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<Factura> listar() {
        return facturaRepository.findAll();
    }

    public Factura guardar(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura buscarPorId(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        facturaRepository.deleteById(id);
    }
}