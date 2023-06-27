package com.example.gestionproduct.controllers;

import com.example.gestionproduct.models.Clientes;
import com.example.gestionproduct.models.Product;
import com.example.gestionproduct.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public List<Clientes> List() {
        return clienteService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> BuscarProducto(@PathVariable(name = "id") long id) {
        try{
            Clientes cliente = clienteService.getById(id);
            return new ResponseEntity<Clientes>(cliente, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Clientes>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Clientes save(@RequestBody  Clientes c) {
        return clienteService.save(c);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Clientes> update(@RequestBody Clientes cliente,@PathVariable(name = "id") long id) {
        try{
            Clientes clienteModify = clienteService.getById(id);
            clienteModify.setTipoIdentificacion(cliente.getTipoIdentificacion());
            clienteModify.setIdentificacion(cliente.getIdentificacion());
            clienteModify.setPrimerNombre(cliente.getPrimerNombre());
            clienteModify.setSegundoNombre(cliente.getSegundoNombre());
            clienteModify.setPrimerApellido(cliente.getPrimerApellido());
            clienteModify.setSegundoApellido(cliente.getSegundoApellido());
            clienteModify.setTelefono(cliente.getTelefono());
            clienteModify.setEmail(cliente.getEmail());
            clienteModify.setDireccion(cliente.getDireccion());
            clienteService.save(clienteModify);
            return new ResponseEntity<Clientes>(clienteModify,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Clientes>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Clientes> delete(@PathVariable(name = "id") long id) {
        try{
            Clientes clienteDelete = clienteService.getById(id);
            clienteService.delete(clienteDelete.getId());
            return new ResponseEntity<Clientes>(clienteDelete,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Clientes>(HttpStatus.NOT_FOUND);
        }
    }
}
