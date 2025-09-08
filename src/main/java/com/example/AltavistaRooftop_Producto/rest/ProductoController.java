package com.example.AltavistaRooftop_Producto.rest;

import com.example.AltavistaRooftop_Producto.model.Producto;
import com.example.AltavistaRooftop_Producto.servicio.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // ✅ Listar todos
    @GetMapping
    public List<Producto> listarTodos() {
        return productoService.listarTodos();
    }

    // ✅ Listar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> listarPorId(@PathVariable Long id) {
        return productoService.listarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Listar por estado
    @GetMapping("/estado/{disponible}")
    public List<Producto> listarPorEstado(@PathVariable Boolean disponible) {
        return productoService.listarPorEstado(disponible);
    }

    // ✅ Crear producto
    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    // ✅ Editar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> editar(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.editar(id, producto));
    }

    // ✅ Eliminar lógico
    @PatchMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarLogico(@PathVariable Long id) {
        productoService.eliminarLogico(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Restaurar lógico
    @PatchMapping("/restaurar/{id}")
    public ResponseEntity<Void> restaurarLogico(@PathVariable Long id) {
        productoService.restaurarLogico(id);
        return ResponseEntity.noContent().build();
    }
}
