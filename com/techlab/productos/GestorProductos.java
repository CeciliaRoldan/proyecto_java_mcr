package com.techlab.productos;
import com.techlab.excepciones.ProductoNoEncontradoException;
import com.techlab.utiles.Utilidades;
import com.techlab.excepciones.*;

import java.util.ArrayList;

public class GestorProductos {
    ArrayList<Producto> productos = new ArrayList<>();
    int contador = 1;

    
    public void agregarNuevoProducto() {
        Producto producto = new Producto(contador);
        actualizarDatosProducto(producto);
        productos.add(producto);
        contador ++;
        System.out.println("Producto agregado correctamente!");
    }

    public String toString() {
        String texto = "\n//======================================//\n"+
                        "//        Productos disponibles:        //\n";
        for (Producto producto : productos) {
            texto += producto.toString();
        }
        texto += "//======================================//\n\n";
        return texto;
    }
   
    public void buscarOActualizarProducto() {
        String texto = Utilidades.inputString("Ingrese el id o nombre del producto: ");

        Producto productoEncontrado = null;
        try {
            int id = Integer.parseInt(texto);
            productoEncontrado = getProducto(id);
        } catch (NumberFormatException e) {
            productoEncontrado = getProducto(texto);
        } 

        if (productoEncontrado != null) {
            System.out.println("Producto encontrado: \n" + productoEncontrado.toString());
            menuActualizarProducto(productoEncontrado);
        } else {
            System.out.println("No se encontro el producto!");
        }
    }
    
    public void eliminarProducto() {
        int id = Utilidades.inputInt("Ingrese el id del producto a eliminar: ");
        
        Producto productoEncontrado = getProducto(id); 
        
        if (productoEncontrado != null) {
            System.out.println("Producto encontrado: \n" + productoEncontrado.toString());
            String eliminar = Utilidades.inputString("Esta seguro que quiere eliminarlo? Ingrese 'S' para confirmarlo: ");

            if (eliminar.toUpperCase().equals("S")) {
                productos.remove(productoEncontrado);
                System.out.println("Producto eliminado correctamente!");
            }
            
        } else {
            System.out.println("No se encontro el producto!");
        }  
    }



    public String obtenerNombreProducto(int id){
        return getProducto(id).getNombre();
    }


    public boolean hayStockSuficiente(int id, int cantidad) {
        return getProducto(id).haySuficiente(cantidad);
    }


    
    private void actualizarDatosProducto(Producto producto) {
        String nombre = Utilidades.inputString("Ingrese el nombre del producto: ");
        nombre = Utilidades.capitalizarTexto(nombre);
                    
        Double precio = Utilidades.inputDouble("Ingrese el precio de " + nombre + ": ");
        // validar precio
                    
        int cantidadEnStock = Utilidades.inputInt("Ingrese la cantidad de " + nombre + ": ");
        // validar cantidad > 0

        producto.setDatos(nombre, precio, cantidadEnStock);
    }


    private void menuActualizarProducto(Producto producto) {
        String texto = 
        "\n Ingrese 1. Actualizar producto " + 
        "\n         2. Volver al menu";

        int input_menu = Utilidades.inputInt(texto);
        if (input_menu == 1) {
            actualizarDatosProducto(producto);
            System.out.println("Producto actualizado correctamente!");
        } 
    }


    public Producto getProducto(int id) {
        for (Producto producto : this.productos) {
            if (producto.tieneId(id)){
                return producto;
            }
        }
        return null;
    }

    private Producto getProducto(String texto) {
        for (Producto producto : this.productos) {
            if (producto.tieneNombre(texto)){
                return producto;
            }
        }
        return null;
    }
    

}
