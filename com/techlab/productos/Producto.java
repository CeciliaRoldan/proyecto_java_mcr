package com.techlab.productos;
import com.techlab.utiles.Utilidades;
import com.techlab.excepciones.StockInsuficienteException;

import java.util.Scanner;

public class Producto {
    int id = 0;
    String nombre = "";
    double precio = 0;
    int cantidad = 0;
    
    public Producto(int id) {
        this.id = id;
    }

    public String toString() {
        String id2esp = String.format("%2s", this.id);
        String nombreEsp = String.format("%-26s", this.nombre);
        String precioEsp = String.format("Precio: $ %-16s", this.precio);
        String cantidadEsp = String.format("Cantidad: %-16s", this.cantidad + " unidades");
        return "//--------------------------------------//\n" +
                "//        :   "         + nombreEsp + "//\n" +
                "//   "+id2esp+"   :   " + precioEsp + "//\n" +
                "//        :   "         + cantidadEsp + "//\n" +
                "//--------------------------------------//\n";
    }


    public boolean haySuficiente(int cantidad) {
        return this.cantidad >= cantidad; 
    }

    public void reducirStock(int cantidad) throws StockInsuficienteException {

        if (this.cantidad < cantidad){
            throw new StockInsuficienteException("No hay suficientes unidades en stock!");
        }
        this.cantidad -= cantidad;
    }

    public void setDatos(String nombre, Double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public boolean tieneId(int id) {
        return this.id == id;
    }
    public boolean tieneNombre(String texto) {
        return this.nombre.equals(Utilidades.capitalizarTexto(texto));
    }

    public String getNombre() {
        return this.nombre;
    }
    public Double getPrecio() {
        return this.precio;
    }
    public int getCantidad() {
        return this.cantidad;
    }

}
