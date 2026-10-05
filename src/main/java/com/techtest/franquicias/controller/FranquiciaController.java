package com.techtest.franquicias.controller;

import com.techtest.franquicias.model.Franquicia;
import com.techtest.franquicias.model.Producto;
import com.techtest.franquicias.model.Sucursal;
import com.techtest.franquicias.service.FranquiciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {

    private final FranquiciaService service;

    public FranquiciaController(FranquiciaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Franquicia> crearFranquicia(@RequestBody Franquicia franquicia) {
        return ResponseEntity.ok(service.crearFranquicia(franquicia));
    }

    @PostMapping("/{franquiciaId}/sucursales")
    public ResponseEntity<Sucursal> agregarSucursal(@PathVariable Long franquiciaId, @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(service.agregarSucursal(franquiciaId, sucursal));
    }

    @PostMapping("/sucursales/{sucursalId}/productos")
    public ResponseEntity<Producto> agregarProducto(@PathVariable Long sucursalId, @RequestBody Producto producto) {
        return ResponseEntity.ok(service.agregarProducto(sucursalId, producto));
    }

    @DeleteMapping("/productos/{productoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long productoId) {
        service.eliminarProducto(productoId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/productos/{productoId}/stock")
    public ResponseEntity<Producto> actualizarStock(@PathVariable Long productoId, @RequestParam Integer stock) {
        return ResponseEntity.ok(service.actualizarStock(productoId, stock));
    }

    @GetMapping("/{franquiciaId}/producto-mayor-stock")
    public ResponseEntity<List<Producto>> obtenerProductoMayorStock(@PathVariable Long franquiciaId) {
        return ResponseEntity.ok(service.obtenerProductoConMayorStockPorFranquicia(franquiciaId));
    }

    @PatchMapping("/{id}/nombre")
    public ResponseEntity<Franquicia> actualizarNombreFranquicia(
            @PathVariable Long id,
            @RequestParam String nuevoNombre) {
        return ResponseEntity.ok(service.actualizarNombreFranquicia(id, nuevoNombre));
    }

    @PatchMapping("/sucursales/{id}/nombre")
    public ResponseEntity<Sucursal> actualizarNombreSucursal(
            @PathVariable Long id,
            @RequestParam String nuevoNombre) {
        return ResponseEntity.ok(service.actualizarNombreSucursal(id, nuevoNombre));
    }

    @PatchMapping("/productos/{id}/nombre")
    public ResponseEntity<Producto> actualizarNombreProducto(
            @PathVariable Long id,
            @RequestParam String nuevoNombre) {
        return ResponseEntity.ok(service.actualizarNombreProducto(id, nuevoNombre));
    }
}