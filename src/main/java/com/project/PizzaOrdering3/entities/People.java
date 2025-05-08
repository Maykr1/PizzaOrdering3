package com.project.PizzaOrdering3.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="PEOPLE")
public class People {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="NAME")
    private String name;

    @Column(name="EMAIL")
    private String email;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private List<Orders> orders;
}
