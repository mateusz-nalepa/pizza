package pl.tu.kielce.pizza.ingredient.service;

import pl.tu.kielce.pizza.ingredient.dto.ItemDto;

import java.util.List;

public interface ItemService {


    ItemDto getById(Long ingredientId);

    ItemDto add(ItemDto itemDto);

    List<ItemDto> findAll();
}
