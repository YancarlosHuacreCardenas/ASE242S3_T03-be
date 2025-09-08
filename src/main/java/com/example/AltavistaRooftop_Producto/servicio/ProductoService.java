package com.example.AltavistaRooftop_Producto.servicio;

import com.example.AltavistaRooftop_Producto.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> listarTodos();

    Optional<Producto> listarPorId(Long id);

    List<Producto> listarPorEstado(Boolean disponible);

    Producto guardar(Producto producto);

    Producto editar(Long id, Producto producto);

    void eliminarLogico(Long id);

    void restaurarLogico(Long id);
}
