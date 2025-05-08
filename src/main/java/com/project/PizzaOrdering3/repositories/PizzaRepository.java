package com.project.PizzaOrdering3.repositories;

import com.project.PizzaOrdering3.entities.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Integer>  {
    
}
