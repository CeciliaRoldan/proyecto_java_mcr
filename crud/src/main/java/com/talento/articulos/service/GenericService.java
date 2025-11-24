
package com.talento.articulos.service;

import java.util.List;
import java.util.Optional;

// Interfaz que define el contrato del servicio
public interface GenericService<T, ID> {
    List<T> listar();
    Optional<T> obtenerPorId(ID id);
    T guardar(T objeto);
    T actualizar(ID id, T elem);
    void eliminar(ID id);
}
