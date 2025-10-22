package com.techlab;

import java.util.Scanner;
import java.util.ArrayList;

import com.techlab.utiles.Utilidades;
import com.techlab.pedidos.GestorPedidos;
import com.techlab.productos.GestorProductos;

public class Menu {

    GestorProductos gestorProductos = new GestorProductos();
    GestorPedidos gestorPedidos = new GestorPedidos(gestorProductos);


    private void agregarProducto() {
        this.gestorProductos.agregarNuevoProducto();
    }
    private void listaProductos() {
        this.gestorProductos.mostrarProductos();
    }
    private void buscarOActualizarProducto() {
        this.gestorProductos.buscarOActualizarProducto();
    }
    private void eliminarProducto() {
        this.gestorProductos.eliminarProducto();
    }
    private void crearPedido() {
        this.gestorPedidos.crearPedido();
    }
    private void listarPedidos() {
        this.gestorPedidos.mostrarPedidos();
    }

    public void ejecutar() {
        
        String menu = "\n" +
        "****************************************** \n" +
        "**                MENU                  ** \n" +
        "****************************************** \n" +
        "**  Selecicone una opcion:              ** \n" +
        "**     1. Agregar Producto              ** \n" +
        "**     2. Listar Productos              ** \n" +
        "**     3. Buscar/Actualizar Producto    ** \n" +
        "**     4. Elimiar Producto              ** \n" +
        "**     5. Crear Pedido                  ** \n" +
        "**     6. Listar Pedidos                ** \n" +
        "**     7. Salir                         ** \n" +
        "****************************************** \n";

        try {
            System.out.println(menu);
            int input_menu = Utilidades.inputInt("Ingrese la opcion correspondiente: ");
            
            while (input_menu != 7) {
                if (input_menu == 1) {
                    agregarProducto();
                } else if (input_menu == 2) {
                    listaProductos();
                } else if (input_menu == 3) {
                    buscarOActualizarProducto();
                } else if (input_menu == 4) {
                    eliminarProducto();
                } else if (input_menu == 5) {
                    crearPedido();
                } else if (input_menu == 6) {
                    listarPedidos();
                };
                
                System.out.println(menu);
                input_menu = Utilidades.inputInt("Ingrese la opcion correspondiente: ");
  
            }
                
        //} catch () {
            //System.out.println("No se encontró el producto: " + e.getMessage());
        } finally {
            System.out.println("Cerrando...");
        }

    }
    
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.ejecutar();
    }
}
