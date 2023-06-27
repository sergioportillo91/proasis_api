package com.example.gestionproduct.service;

import com.example.gestionproduct.models.Clientes;
import com.example.gestionproduct.models.Product;
import com.example.gestionproduct.repository.crud.ClienteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;


    public List<Clientes> getAll() {
        return (List<Clientes>) clienteCrudRepository.findAll();
    }

    public Clientes save(Clientes cliente){
        return  clienteCrudRepository.save(cliente);
    }

    public Clientes getById(Long id){
        return clienteCrudRepository.findById(id).get();
    }

    public void delete(Long id){
        clienteCrudRepository.deleteById(id);
    }

}
