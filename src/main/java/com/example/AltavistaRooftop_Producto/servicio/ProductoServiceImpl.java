package com.example.AltavistaRooftop_Producto.servicio;

import com.example.AltavistaRooftop_Producto.model.Producto;
import com.example.AltavistaRooftop_Producto.repositorio.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> listarPorId(Long id) {
        return productoRepository.findById(id.intValue());
    }

    @Override
    public List<Producto> listarPorEstado(Boolean disponible) {
        return productoRepository.findByDisponible(disponible);
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto editar(Long id, Producto producto) {
        Producto existente = productoRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        existente.setCategoria(producto.getCategoria());
        existente.setDisponible(producto.getDisponible());
        existente.setImagenUrl(producto.getImagenUrl());

        return productoRepository.save(existente);
    }

    @Override
    public void eliminarLogico(Long id) {
        Producto producto = productoRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        producto.setDisponible(false);
        productoRepository.save(producto);
    }

    @Override
    public void restaurarLogico(Long id) {
        Producto producto = productoRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        producto.setDisponible(true);
        productoRepository.save(producto);
    }
}
