package com.project.PizzaOrdering3.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersRequestDTO {
    private String customerName;
    private Boolean completed;
    private List<Integer> pizzaIds;
}
