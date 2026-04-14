package carrito.controller;

import carrito.model.DetalleFactura;
import carrito.service.DetalleFacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/detalles")
public class DetalleFacturaController {

    private final DetalleFacturaService detalleFacturaService;

    public DetalleFacturaController(DetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    @GetMapping
    public List<DetalleFactura> listar() {
        return detalleFacturaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> buscar(@PathVariable Long id) {
        DetalleFactura detalle = detalleFacturaService.buscarPorId(id);
        return detalle != null ? ResponseEntity.ok(detalle) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public DetalleFactura crear(@RequestBody DetalleFactura detalle) {
        return detalleFacturaService.guardar(detalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleFactura> actualizar(@PathVariable Long id, @RequestBody DetalleFactura detalle) {
        if (detalleFacturaService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        detalle.setId(id);
        return ResponseEntity.ok(detalleFacturaService.guardar(detalle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleFacturaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}