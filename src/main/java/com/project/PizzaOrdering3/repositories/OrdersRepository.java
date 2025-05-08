package com.project.PizzaOrdering3.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.PizzaOrdering3.entities.Orders;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {
    
}
