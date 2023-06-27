package com.example.gestionproduct.repository.crud;

import org.springframework.data.repository.CrudRepository;

import com.example.gestionproduct.models.Product;

public interface ProductCrudRepository extends CrudRepository<Product, Long> {

}
