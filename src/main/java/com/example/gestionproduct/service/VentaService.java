package com.example.gestionproduct.service;

import com.example.gestionproduct.models.Product;
import com.example.gestionproduct.models.Ventas;
import com.example.gestionproduct.repository.crud.VentaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaCrudRepository ventaCrudRepository;

    public List<Ventas> listar() {
        return (List<Ventas>) ventaCrudRepository.findAll();
    }

    public Ventas guardar(Ventas venta){
        return  ventaCrudRepository.save(venta);
    }

}
