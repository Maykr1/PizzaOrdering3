package com.project.PizzaOrdering3.controllers;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;


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
    //curl http://localhost:8080/pizzas

    @GetMapping("/{id}")
    public Optional<Pizza> getPizzaById(@PathVariable("id") Integer id) {
        return this.pizzaRepository.findById(id);
    }
    //curl http://localhost:8080/pizzas/5

    @GetMapping("/search")
    public List<Pizza> searchPizzas(
        @RequestParam(name="name", required=false) String name,
        @RequestParam(name="size", required=false) String size,
        @RequestParam(name="price", required=false) Double price
    ) {
        if (name != null) {
            return this.pizzaRepository.findByName(name);
        } else if (size != null) {
            return this.pizzaRepository.findBySize(size);
        } else if (price != null) {
            return this.pizzaRepository.findByPrice(price);
        } else {
            return new ArrayList<>();
        }
    }
    //Can search for something like curl http://localhost:8080/pizzas/search?name=Pepperoni%20Pizza

    @PostMapping("")
    public Pizza createPizza(@RequestBody Pizza pizza) {
        Pizza newPizza = this.pizzaRepository.save(pizza);
        return newPizza;
    }
    //curl -X POST http://localhost:8080/pizzas -H "Content-Type: application/json" -d '{"name": "Three Cheese Pizza", "size": "medium", "price": 10.50}'

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

    //curl -X PUT http://localhost:8080/pizzas/4 -H "Content-Type: application/json" -d '{"name": "Three Cheese Pizza", "size": "medium", "price": 10.50}'

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
    //curl -X DELETE http://localhost:8080/pizzas/5
}
