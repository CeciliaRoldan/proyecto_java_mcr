
package com.talento.articulos.model;

import jakarta.persistence.*;


@Entity
@Table(name = "articulos")
public class Articulo {

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nombre;
    private Double precio;
    private int cantidad;
    private String imagen;

    public Articulo() {}

    public Articulo(Long id, String nombre, Double precio, String imagen, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.cantidad = cantidad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = capitalizarTexto(nombre); }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen;} 


    @Override
    public String toString() {
        return "Articulo: "+this.id+": "+this.nombre+" $"+this.precio+" "+this.cantidad+" unidades.";
    }


    public void validarArticulo() {
        if (precio == null || precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0");
        } 
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
    }

    public void reducirStock(int cant) {
        if (this.cantidad < cant) {
            throw new IllegalArgumentException("Stock insuficiente!");
        }
        this.cantidad -= cant;
    }

    public void aumentarStock(int cant) {
        this.cantidad += cant;
    }

    private static String capitalizarTexto(String nombre) {
        nombre = nombre.trim().toLowerCase();
        if (nombre.isEmpty()) {
            return "";
        }
        String primeraLetra = nombre.substring(0, 1).toUpperCase();
        String resto = nombre.substring(1);
        return primeraLetra + resto;
    }
}
