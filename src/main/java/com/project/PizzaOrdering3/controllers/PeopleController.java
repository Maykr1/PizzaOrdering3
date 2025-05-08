package com.project.PizzaOrdering3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.PizzaOrdering3.dto.PeopleRequestDTO;
import com.project.PizzaOrdering3.entities.Orders;
import com.project.PizzaOrdering3.entities.People;
import com.project.PizzaOrdering3.repositories.OrdersRepository;
import com.project.PizzaOrdering3.repositories.PeopleRepository;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("")
    public Iterable<People> getAllPeople() {
        return this.peopleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<People> getPeopleById(@PathVariable("id") Integer id) {
        return this.peopleRepository.findById(id);
    }

    @PostMapping("")
    public People createPeople(@RequestBody PeopleRequestDTO peopleRequest) {
        People person = new People();
        person.setName(peopleRequest.getName());
        person.setEmail(peopleRequest.getEmail());
        person.setPhoneNumber(peopleRequest.getPhoneNumber());
        
        List<Orders> orders = (List<Orders>) ordersRepository.findAllById(peopleRequest.getOrderIds());
        person.setOrders(orders);

        return peopleRepository.save(person);

    }

    @PutMapping("/{id}")
    public People updatePeople(@PathVariable("id") Integer id, @RequestBody People person) {
        Optional<People> personToUpdateOptional = this.peopleRepository.findById(id);

        if (!personToUpdateOptional.isPresent()) {
            return null;
        }

        People personToUpdate = personToUpdateOptional.get();

        if (person.getName() != null) {
            personToUpdate.setName(person.getName());
        }
        if (person.getEmail() != null) {
            personToUpdate.setEmail(person.getEmail());
        }
        if (person.getPhoneNumber() != null) {
            personToUpdate.setPhoneNumber(person.getPhoneNumber());
        }
        if (person.getOrders() != null) {
            personToUpdate.setOrders((person.getOrders()));
        }

        return this.peopleRepository.save(personToUpdate);
    }
}
