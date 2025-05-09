package com.project.PizzaOrdering3.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.PizzaOrdering3.dto.OrderDTO;
import com.project.PizzaOrdering3.entities.Orders;
import com.project.PizzaOrdering3.entities.Pizza;
import com.project.PizzaOrdering3.repositories.OrdersRepository;
import com.project.PizzaOrdering3.repositories.PizzaRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("")
    public Iterable<Orders> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Orders> getOrderById(@PathVariable("id") Integer id) {
        return this.orderRepository.findById(id);
    }

    @PostMapping("")
    public Orders createOrder(@RequestBody OrderDTO orderDTO) {
        Orders order = new Orders();
        
        if (orderDTO.getPizzaIds() != null && !orderDTO.getPizzaIds().isEmpty()) {
            List<Pizza> pizzas = (List<Pizza>) pizzaRepository.findAllById(orderDTO.getPizzaIds());
            order.setPizzas(pizzas);
        } else {
            order.setPizzas(new ArrayList<>());
        }

        order.setCompleted(orderDTO.getCompleted());

        Orders newOrder = this.orderRepository.save(order);
        return orderRepository.save(newOrder);
    }

    @PutMapping("/{id}")
    public Orders updateOrder(@PathVariable("id") Integer id, @RequestBody OrderDTO orderDTO) {
        Optional<Orders> orderToUpdateOptional = this.orderRepository.findById(id);
        
        if (!orderToUpdateOptional.isPresent()) {
            return null;
        }

        Orders orderToUpdate = orderToUpdateOptional.get();

        if (orderDTO.getPizzaIds() != null) {
            List<Pizza> pizzas = (List<Pizza>) pizzaRepository.findAllById(orderDTO.getPizzaIds());
            orderToUpdate.setPizzas(pizzas);
        }
        if (orderDTO.getCompleted() != null) {
            orderToUpdate.setCompleted(orderDTO.getCompleted());
        }

        Orders updatedOrder = this.orderRepository.save(orderToUpdate);
        return updatedOrder;
    }

    @PutMapping("/{id}/status") // <-- cannot just be @PutMapping("/{id}") as it conflicts with the one above
    public Orders updateCompleted(@PathVariable("id") Integer id, @RequestParam Boolean completed) {
        Optional<Orders> orderToUpdateOptional = this.orderRepository.findById(id);

        if (!orderToUpdateOptional.isPresent()) {
            return null;
        }

        Orders orderToUpdate = orderToUpdateOptional.get();

        if (completed != null) {
            orderToUpdate.setCompleted(completed);
        }

        Orders updatedOrder = this.orderRepository.save(orderToUpdate);
        return updatedOrder;
    }
    //curl -X PUT "http://localhost:8080/orders/1?completed=true"

    @Transactional
    @DeleteMapping("/{id}")
    public Orders deleteOrder(@PathVariable("id") Integer id) {
        Optional<Orders> orderToDeleteOptional = this.orderRepository.findById(id);

        if (!orderToDeleteOptional.isPresent()) {
            return null;
        }

        Orders orderToDelete = orderToDeleteOptional.get();
        orderToDelete.getPizzas().clear();
        orderRepository.save(orderToDelete);

        this.orderRepository.delete(orderToDelete);
        return orderToDelete;
    }
}
