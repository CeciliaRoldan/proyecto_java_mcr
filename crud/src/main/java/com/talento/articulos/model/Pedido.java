
package com.talento.articulos.model;

import jakarta.persistence.*;


@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "id_articulo")
    private Long idArticulo;

    private int cantidad;

    public Pedido() {}

    public Pedido(Long id, Long idPedido, Long idArticulo, int cantidad) {
        this.id = id;
        this.idPedido = idPedido;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }
    public Long getIdArticulo() { return idArticulo; }
    public void setIdArticulo(Long idArticulo) { this.idArticulo = idArticulo; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }



    public String toString() {
        return "Pedido "+this.idPedido+": Articulo "+this.idArticulo+" "+this.cantidad+" unidades.";
    }


}
