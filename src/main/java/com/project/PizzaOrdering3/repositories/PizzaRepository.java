package com.project.PizzaOrdering3.repositories;

import com.project.PizzaOrdering3.entities.Pizza;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Integer>  {

    List<Pizza> findByName(String name);
    List<Pizza> findBySize(String size);
    List<Pizza> findByPrice(Double price);
}
