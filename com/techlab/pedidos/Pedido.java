package com.techlab.pedidos;
import com.techlab.productos.Producto;
import com.techlab.utiles.Utilidades;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import com.techlab.productos.Producto;

public class Pedido {
    int id;
    ArrayList<Producto> productos = new ArrayList<>();
    
    public Pedido(int id) {
        this.id = id;
    }

    public void agregarProducto(String nombre, Double precio, int cantidad) {
        Producto producto = new Producto(0);
        producto.setDatos(nombre, precio, cantidad);
        productos.add(producto);
    }

    public String toString() {
        String id2esp = String.format("%2s", this.id);
        String costoEsp = String.format("%-21s", getCostoTotal());
        String texto = "//======================================//\n" +
                        "//              Pedido "+id2esp+"               //\n";

        for (Producto producto : productos) {
            texto += producto.toString();
        }

        texto += "//  Costo total: $ "+ costoEsp +"//\n" +
                "//======================================//\n\n";
        return texto;
    }
    
    private Double getCostoTotal() {
        Double costo = 0.0;
        for (Producto producto : productos) {
            costo += producto.getPrecio() * producto.getCantidad();
        }
        return costo;
    }
   
}
