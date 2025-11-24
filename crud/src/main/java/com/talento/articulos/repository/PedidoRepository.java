// Paquete donde se encuentra la clase
package com.talento.articulos.repository;

// Importamos el modelo de datos Articulo
import com.talento.articulos.model.Pedido;

// Importamos JpaRepository, que nos da los métodos CRUD listos
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// Importamos la anotación @Repository que marca esta interfaz como componente de acceso a datos
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Esta interfaz se conecta con la base de datos y maneja operaciones sobre la entidad Articulo
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // ===============================================
    // 🚀 MÉTODOS CRUD INCLUIDOS AUTOMÁTICAMENTE
    // ===============================================
    // findAll()                -> Lista todos los artículos
    // findById(Long id)        -> Busca uno por ID
    // save(Articulo a)         -> Inserta o actualiza
    // deleteById(Long id)      -> Elimina por ID
    // count()                  -> Cuenta registros
    // existsById(Long id)      -> Verifica si existe un ID

    // ===============================================
    // 🛠️ MÉTODOS PERSONALIZADOS (se generan por nombre)
    // ===============================================
    @Query("select coalesce(max(idPedido), 0) from Pedido")
    Long getUltimoIdPedido();

    List<Pedido> findByIdPedido(Long idPedido);
    Optional<Pedido> findFirstByIdPedido(Long idPedido);
    Optional<Pedido> findByIdPedidoAndIdArticulo(Long idPedido, Long idArticulo);
    
    @Transactional
    void deleteByIdPedido(Long idPedido);
}


/*
 
    // Buscar artículos por nombre exacto
    List<Articulo> findByNombre(String nombre);

    // Buscar artículos cuyo nombre contenga una palabra (LIKE '%texto%')
    List<Articulo> findByNombreContaining(String texto);

    // Buscar artículos con precio mayor a un valor dado
    List<Articulo> findByPrecioGreaterThan(Double precio);

    // Buscar artículos con precio entre dos valores
    List<Articulo> findByPrecioBetween(Double min, Double max);

    // Buscar por nombre ignorando mayúsculas y minúsculas
    List<Articulo> findByNombreIgnoreCase(String nombre);

    // Buscar artículos ordenados por precio ascendente
    List<Articulo> findAllByOrderByPrecioAsc();

    // Buscar artículos por nombre y precio mayor a cierto valor
    List<Articulo> findByNombreAndPrecioGreaterThan(String nombre, Double precio);
 * 
 * 
 * 
 */
