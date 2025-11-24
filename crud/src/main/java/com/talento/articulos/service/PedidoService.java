package com.talento.articulos.service;

import java.util.List;
import java.util.Optional;

import com.talento.articulos.model.Pedido;

public interface PedidoService extends GenericService<Pedido, Long>  {
    public Optional<Pedido> obtenerPorIdPedidoIdArticulo(Long idPedido, Long idArticulo);
}
