
package com.talento.articulos.controller;

import com.talento.articulos.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/api") 
public abstract class GenericController<T, ID> {

    private final GenericService<T, ID> genericService;

    @Autowired
    public GenericController(GenericService<T, ID> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public List<T> listar() {
        return genericService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> obtenerPorId(@PathVariable ID id) {
        return genericService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody T elem) {
        try {
            T nuevoElem = genericService.guardar(elem);
            return ResponseEntity.ok(nuevoElem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable ID id, @RequestBody T elem) {
        if (genericService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            T nuevoElem = genericService.actualizar(id, elem);
            return ResponseEntity.ok(nuevoElem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable ID id) {
        if (genericService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        genericService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
