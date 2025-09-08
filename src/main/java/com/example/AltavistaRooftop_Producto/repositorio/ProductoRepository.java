package com.example.AltavistaRooftop_Producto.repositorio;

import com.example.AltavistaRooftop_Producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByDisponible(Boolean disponible);
}
