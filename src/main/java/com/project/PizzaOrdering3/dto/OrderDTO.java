package com.project.PizzaOrdering3.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private List<Integer> pizzaIds;
    private Boolean completed;
    private Integer personId;
}
