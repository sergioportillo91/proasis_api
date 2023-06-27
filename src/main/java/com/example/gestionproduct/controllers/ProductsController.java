package com.example.gestionproduct.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestionproduct.models.Product;
import com.example.gestionproduct.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping("/listar")
    public List<Product> List() {
        return productService.listarProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> BuscarProducto(@PathVariable(name = "id") long id) {
        try{
            Product product = productService.obtenerProductoPorID(id);
            return new ResponseEntity<Product>(product,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@RequestBody  Product producto) {
        return productService.guardarProducto(producto);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Product> update(@RequestBody Product producto,@PathVariable(name = "id") long id) {
        try{
            Product productModificar = productService.obtenerProductoPorID(id);
            productModificar.setNombre(producto.getNombre());
            productModificar.setDescripcion(producto.getDescripcion());
            productModificar.setStock(producto.getStock());
            productModificar.setPrecio(producto.getPrecio());
            productModificar.setUnidad(producto.getUnidad());
            productService.guardarProducto(productModificar);
            return new ResponseEntity<Product>(productModificar,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/actualizarStock/{id}/{cantidad}")
    public ResponseEntity<Product> updateStock(@PathVariable(name = "id") long id, @PathVariable(name = "cantidad") int cantidad) {
        try {
            Product producto = productService.obtenerProductoPorID(id);
            int stockActualizado = producto.getStock() - cantidad;
            producto.setStock(stockActualizado);
            productService.guardarProducto(producto);
            return new ResponseEntity<Product>(producto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable(name = "id") long id) {
        try{
            Product productEliminar = productService.obtenerProductoPorID(id);
            productService.eliminarProducto(productEliminar.getId());
            return new ResponseEntity<Product>(productEliminar,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }
}
