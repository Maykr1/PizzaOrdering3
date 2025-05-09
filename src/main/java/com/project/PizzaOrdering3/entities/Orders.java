package com.project.PizzaOrdering3.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="ORDERS")
public class Orders {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany
    @JoinTable(
        name="order_pizza",
        joinColumns = @JoinColumn(name="order_id"),
        inverseJoinColumns = @JoinColumn(name="pizza_id")
    )
    private List<Pizza> pizzas;

    @Column(name="COMPLETED")
    private Boolean completed;
}
