
package com.talento.articulos.service;

import com.talento.articulos.model.Articulo;
import com.talento.articulos.model.Pedido;
import com.talento.articulos.repository.ArticuloRepository;
import com.talento.articulos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service 
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ArticuloRepository articuloRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ArticuloRepository articuloRepository) {
        this.pedidoRepository = pedidoRepository;
        this.articuloRepository = articuloRepository;
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obtenerPorId(Long id) {
        return pedidoRepository.findFirstByIdPedido(id);
    }

    public Optional<Pedido> obtenerPorIdPedidoIdArticulo(Long idPedido, Long idArticulo) {
        return pedidoRepository.findByIdPedidoAndIdArticulo(idPedido, idArticulo);
    }

    public Pedido guardar(Pedido pedido) {
        Long idPedido = pedidoRepository.getUltimoIdPedido() + 1;
        pedido.setIdPedido(idPedido);
        pedido.setCantidad(1);
        reducirStockArticulo(pedido.getIdArticulo(), 1);
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizar(Long id, Pedido pedido) {
        Optional<Pedido> auxPedido = obtenerPorIdPedidoIdArticulo(pedido.getIdPedido(), pedido.getIdArticulo());

        // Si no existe el articulo lo agrego
        if (auxPedido.isEmpty()) {
            pedido.setCantidad(1);
           
        } else { // Si ya existe el articulo le agrego 1
            pedido.setId(auxPedido.get().getId());
            pedido.setCantidad(auxPedido.get().getCantidad() + 1);
        }
        reducirStockArticulo(pedido.getIdArticulo(), 1);

        return pedidoRepository.save(pedido);
    }

    public void eliminar(Long idPedido) {
        // Se aumenta la cantidad de stock que tenia el pedido de vuelta al articulo
        List<Pedido> pedidos = pedidoRepository.findByIdPedido(idPedido);
        for (Pedido p: pedidos) {
            Articulo articulo = articuloRepository.findById(p.getIdArticulo()).get();
            articulo.aumentarStock(p.getCantidad());
            articuloRepository.save(articulo);
        }

        pedidoRepository.deleteByIdPedido(idPedido);
    }
    

    private void reducirStockArticulo(Long idArticulo, int cantidad)  {
        Articulo articulo = articuloRepository.findById(idArticulo).get();
        articulo.reducirStock(cantidad);
        articuloRepository.save(articulo);
    }
}
