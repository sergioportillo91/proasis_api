package com.example.gestionproduct.repository.crud;

import com.example.gestionproduct.models.Product;
import com.example.gestionproduct.models.Ventas;
import org.springframework.data.repository.CrudRepository;

public interface VentaCrudRepository  extends CrudRepository<Ventas, Long> {
}
