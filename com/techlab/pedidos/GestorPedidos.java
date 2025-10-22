package com.techlab.pedidos;
import com.techlab.utiles.Utilidades;
import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.GestorProductos;
import com.techlab.productos.Producto;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorPedidos {
    GestorProductos gestorProductos;
    ArrayList<Pedido> pedidos = new ArrayList<>();
    int contador = 1;

    public GestorPedidos(GestorProductos gestorProductos) {
        this.gestorProductos = gestorProductos;
    }

    
    public void crearPedido() {
        
        int input_menu = 1;
        String texto = 
        "\n Ingrese 1. Agregar otro producto " + 
        "\n         2. Terminar el pedido";

        Pedido pedido = new Pedido(contador);

        while (input_menu == 1) {
            agregarProductoAlPedido(pedido);
            input_menu = Utilidades.inputInt(texto);
            contador ++;
        }

        pedidos.add(pedido);
        System.out.println("Pedido creado correctamente!");
    }


        
    private void agregarProductoAlPedido(Pedido pedido) {

        int id = Utilidades.inputInt("Ingrese el ID del producto: ");
        Producto producto = gestorProductos.getProducto(id);

        if (producto != null) {
            int cantidad = Utilidades.inputInt("Ingrese la cantidad de " + producto.getNombre() + ": ");
            
            try {
                producto.reducirStock(cantidad);
                pedido.agregarProducto(producto.getNombre(), producto.getPrecio(), cantidad);
                
            } catch (StockInsuficienteException e) {
                System.out.println(e.getMessage());
            } 
        }

    }


    public void mostrarPedidos() {
        System.out.println("\n¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡");
        System.out.println("||            Lista de pedidos          ||");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        for (Pedido pedido : pedidos) {
            pedido.imprimir();
        }
    }

}
