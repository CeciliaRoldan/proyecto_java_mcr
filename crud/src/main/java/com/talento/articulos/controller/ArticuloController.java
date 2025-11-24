
package com.talento.articulos.controller;

import com.talento.articulos.model.Articulo;
import com.talento.articulos.model.Pedido;
import com.talento.articulos.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/api/articulos") 
public class ArticuloController extends GenericController<Articulo, Long> {


    @Autowired
    public ArticuloController(GenericService<Articulo, Long> articuloService) {
        super(articuloService);
    }

}
