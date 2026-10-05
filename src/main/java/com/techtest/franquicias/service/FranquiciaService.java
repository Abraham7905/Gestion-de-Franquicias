package com.techtest.franquicias.service;
import com.techtest.franquicias.model.Franquicia;
import com.techtest.franquicias.model.Producto;
import com.techtest.franquicias.model.Sucursal;
import com.techtest.franquicias.repository.FranquiciaRepository;
import com.techtest.franquicias.repository.ProductoRepository;
import com.techtest.franquicias.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranquiciaService {

    private final FranquiciaRepository franquiciaRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    public FranquiciaService(FranquiciaRepository franquiciaRepository,
                             SucursalRepository sucursalRepository,
                             ProductoRepository productoRepository) {
        this.franquiciaRepository = franquiciaRepository;
        this.sucursalRepository = sucursalRepository;
        this.productoRepository = productoRepository;
    }

    // 1. Agregar Franquicia
    public Franquicia crearFranquicia(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    // 2. Agregar Sucursal a una Franquicia
    public Sucursal agregarSucursal(Long franquiciaId, Sucursal sucursal) {
        Franquicia franquicia = franquiciaRepository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));
        sucursal.setFranquicia(franquicia);
        return sucursalRepository.save(sucursal);
    }

    // 3. Agregar Producto a una Sucursal[cite: 1]
    public Producto agregarProducto(Long sucursalId, Producto producto) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        producto.setSucursal(sucursal);
        return productoRepository.save(producto);
    }

    // 4. Eliminar Producto de una Sucursal[cite: 1]
    public void eliminarProducto(Long productoId) {
        if (!productoRepository.existsById(productoId)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productoRepository.deleteById(productoId);
    }

    // 5. Modificar Stock de un Producto[cite: 1]
    public Producto actualizarStock(Long productoId, Integer nuevoStock) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setStock(nuevoStock);
        return productoRepository.save(producto);
    }

    // 6. Producto con más stock por sucursal para una franquicia[cite: 1]
    // (Utilizamos programación funcional con Streams)
    public List<Producto> obtenerProductoConMayorStockPorFranquicia(Long franquiciaId) {
        Franquicia franquicia = franquiciaRepository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));

        return franquicia.getSucursales().stream()
                .map(sucursal -> sucursal.getProductos().stream()
                        .max((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock()))
                        .orElse(null))
                .filter(producto -> producto != null)
                .toList();
    }

    public Franquicia actualizarNombreFranquicia(Long id, String nuevoNombre) {
        Franquicia franquicia = franquiciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));
        franquicia.setNombre(nuevoNombre);
        return franquiciaRepository.save(franquicia);
    }

    public Sucursal actualizarNombreSucursal(Long id, String nuevoNombre) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        sucursal.setNombre(nuevoNombre);
        return sucursalRepository.save(sucursal);
    }

    public Producto actualizarNombreProducto(Long id, String nuevoNombre) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setNombre(nuevoNombre);
        return productoRepository.save(producto);
    }
}