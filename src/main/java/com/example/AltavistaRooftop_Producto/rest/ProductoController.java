package com.example.AltavistaRooftop_Producto.rest;

import com.example.AltavistaRooftop_Producto.model.Producto;
import com.example.AltavistaRooftop_Producto.servicio.ProductoService;
import org.springframework.http.HttpStatus; // Importar HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // Importar Optional

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Permite CORS desde cualquier origen. Considera restringir en producción.
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // ✅ Listar todos los productos
    @GetMapping
    public List<Producto> listarTodos() {
        return productoService.listarTodos();
    }

    // ✅ Listar producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> listarPorId(@PathVariable Long id) {
        Optional<Producto> productoOptional = productoService.listarPorId(id);
        // Si se encuentra el producto, lo devolvemos con status 200 OK.
        // Si no se encuentra, devolvemos 404 Not Found.
        return productoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // ✅ Listar productos por estado (disponible o no)
    @GetMapping("/estado/{disponible}")
    public List<Producto> listarPorEstado(@PathVariable Boolean disponible) {
        // Asumiendo que el servicio implementa esta lógica
        return productoService.listarPorEstado(disponible);
    }

    // ✅ Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        try {
            Producto productoCreado = productoService.guardar(producto);
            // Devolvemos el producto creado con status 201 Created (más semántico para creación)
            return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
        } catch (Exception e) {
            // Manejo genérico de errores para la creación.
            // Podrías querer lanzar excepciones más específicas en el servicio.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // ✅ Editar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> editar(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Producto productoActualizado = productoService.editar(id, producto);
            // Devolvemos el producto actualizado con status 200 OK.
            return ResponseEntity.ok(productoActualizado);
        } catch (RuntimeException e) {
            // Si el servicio lanza una excepción (ej: producto no encontrado), devolvemos 404.
            System.err.println("Error al editar producto: " + e.getMessage()); // Log del error
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Para otros errores inesperados durante la edición.
            System.err.println("Error inesperado al editar producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // ✅ Eliminar un producto lógicamente (cambia su estado a no disponible)
    @PatchMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarLogico(@PathVariable Long id) {
        try {
            productoService.eliminarLogico(id);
            // Devolvemos 204 No Content si la operación fue exitosa.
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Si el producto no se encuentra.
            System.err.println("Error al eliminar producto lógicamente: " + e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Otros errores.
            System.err.println("Error inesperado al eliminar producto lógicamente: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ✅ Restaurar un producto lógicamente (cambia su estado a disponible)
    @PatchMapping("/restaurar/{id}")
    public ResponseEntity<Void> restaurarLogico(@PathVariable Long id) {
        try {
            productoService.restaurarLogico(id);
            // Devolvemos 204 No Content si la operación fue exitosa.
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Si el producto no se encuentra.
            System.err.println("Error al restaurar producto lógicamente: " + e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Otros errores.
            System.err.println("Error inesperado al restaurar producto lógicamente: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}