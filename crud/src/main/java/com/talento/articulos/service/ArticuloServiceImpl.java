
package com.talento.articulos.service;

import com.talento.articulos.model.Articulo;
import com.talento.articulos.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marca la clase como servicio de Spring
public class ArticuloServiceImpl implements GenericService<Articulo, Long> {

    private final ArticuloRepository articuloRepository;

    @Autowired
    public ArticuloServiceImpl(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public List<Articulo> listar() {
        return articuloRepository.findAll();
    }

    public Optional<Articulo> obtenerPorId(Long id) {
        return articuloRepository.findById(id);
    }

    public Articulo guardar(Articulo articulo) {
        try {
            articulo.validarArticulo();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al validar artículo: " + e.getMessage());
        }
        return articuloRepository.save(articulo);
    }

    public Articulo actualizar(Long id, Articulo articulo) {
        articulo.setId(id);
        return guardar(articulo);
    }

    public void eliminar(Long id) {
        articuloRepository.deleteById(id);
    }

}
