package com.project.PizzaOrdering3.dto;

import java.util.List;

public class OrdersRequestDTO {
    private String customerName;
    private List<Integer> pizzaIds;
    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Integer> getPizzaIds() {
        return pizzaIds;
    }
    
    public void setPizzaIds(List<Integer> pizzaIds) {
        this.pizzaIds = pizzaIds;
    }
}
