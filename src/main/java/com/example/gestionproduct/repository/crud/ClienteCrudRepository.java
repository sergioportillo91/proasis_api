package com.example.gestionproduct.repository.crud;

import com.example.gestionproduct.models.Clientes;
import org.springframework.data.repository.CrudRepository;

public interface ClienteCrudRepository  extends CrudRepository<Clientes,Long> {
}
