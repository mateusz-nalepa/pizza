package pl.tu.kielce.pizza.common.pizza.service;


import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;

import java.util.List;

public interface PizzaService {

    PizzaDto findOne(Long pizzaId);

    PizzaDto create(PizzaDto pizzaDto);

    List<PizzaDto> findAll();
}
