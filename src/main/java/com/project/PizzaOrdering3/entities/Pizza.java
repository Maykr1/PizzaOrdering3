package com.project.PizzaOrdering3.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="Pizza")
public class Pizza {
    // id, name, size, price
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="NAME")
    private String name;
    
    @Column(name="SIZE")
    private String size;

    @Column(name="PRICE")
    private Double price;

    @ManyToMany(mappedBy="pizzas")
    @JsonIgnore
    private List<Orders> orders;
}