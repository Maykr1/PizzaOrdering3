package com.project.PizzaOrdering3.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeopleRequestDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private List<Integer> orderIds;
}
