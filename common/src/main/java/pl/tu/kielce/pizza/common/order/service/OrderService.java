package pl.tu.kielce.pizza.common.order.service;

import pl.tu.kielce.pizza.common.order.dto.AddItemDto;
import pl.tu.kielce.pizza.common.order.dto.AddPizzaDto;

public interface OrderService {

    void addPizzaToOrder(AddPizzaDto addPizzaDto);

    void addItemToOrder(AddItemDto addItemDto);
}
