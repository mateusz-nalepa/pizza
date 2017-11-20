package pl.tu.kielce.pizza.common.item.service;


import pl.tu.kielce.pizza.common.item.dto.ItemDto;

import java.util.List;

public interface ItemService {

    ItemDto findOne(Long itemId);

    ItemDto create(ItemDto itemDto);

    List<ItemDto> findAll();

    List<ItemDto> findAllWithMultiplier();
}
