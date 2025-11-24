
package com.talento.articulos.controller;

import com.talento.articulos.model.Pedido;
import com.talento.articulos.service.GenericService;
import com.talento.articulos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/api/pedidos") 
public class PedidoController extends GenericController<Pedido, Long> {

    private final PedidoService pedidoService;
    
    @Autowired
    public PedidoController(PedidoService pedidoService) {
        super(pedidoService);
        this.pedidoService = pedidoService;
    }

    @PutMapping("/{idPedido}")
    public ResponseEntity<?> actualizar(@PathVariable Long idPedido, @RequestBody Pedido elem) {
        if (pedidoService.obtenerPorId(idPedido).isEmpty()) {
            return ResponseEntity.ok(pedidoService.guardar(elem));
        }
        try {
            return ResponseEntity.ok(pedidoService.actualizar(idPedido, elem));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }
}
