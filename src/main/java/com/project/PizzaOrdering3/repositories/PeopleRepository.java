package com.project.PizzaOrdering3.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.PizzaOrdering3.entities.People;

public interface PeopleRepository extends CrudRepository<People, Integer> {

}
