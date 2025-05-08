package com.project.PizzaOrdering3.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.PizzaOrdering3.entities.Pizza;
import com.project.PizzaOrdering3.repositories.PizzaRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    /*
    private final PizzaRepository pizzaRepository;

    public PizzaController(final PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }
    */

    //This is a long drawn out version of just doing this:
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("")
    public Iterable<Pizza> getAllPizzas() {
        return this.pizzaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pizza> getPizzaById(@PathVariable("id") Integer id) {
        return this.pizzaRepository.findById(id);
    }
    

    @PostMapping("")
    public Pizza createPizza(@RequestBody Pizza pizza) {
        Pizza newPizza = this.pizzaRepository.save(pizza);
        return newPizza;
    }

    @PutMapping("/{id}")
    public Pizza updatePizza(@PathVariable("id") Integer id, @RequestBody Pizza pizza) {
        Optional<Pizza> pizzaToUpdateOptional = this.pizzaRepository.findById(id);

        if (!pizzaToUpdateOptional.isPresent()) {
            return null;
        }

        Pizza pizzaToUpdate = pizzaToUpdateOptional.get();

        if (pizza.getName() != null) {
            pizzaToUpdate.setName(pizza.getName());
        }
        if (pizza.getSize() != null) {
            pizzaToUpdate.setSize(pizza.getSize());
        }
        if (pizza.getPrice() != null) {
            pizzaToUpdate.setPrice(pizza.getPrice());
        }

        Pizza updatedPizza = this.pizzaRepository.save(pizzaToUpdate);
        return updatedPizza;
    }

    @DeleteMapping("/{id}")
    public Pizza deletePizza(@PathVariable("id") Integer id) {
        Optional<Pizza> pizzaToDeleteOptional = this.pizzaRepository.findById(id);

        if(!pizzaToDeleteOptional.isPresent()) {
            return null;
        }

        Pizza pizzaToDelete = pizzaToDeleteOptional.get();
        this.pizzaRepository.delete(pizzaToDelete);
        return pizzaToDelete;
    }
}
