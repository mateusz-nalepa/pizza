package pl.tu.kielce.pizza.be.item.service;

import pl.tu.kielce.pizza.common.item.dto.ItemDto;

abstract class BaseItemServiceTest
{
    ItemDto exampleItem()
    {
        return ItemDto
            .builder()
            .name("Example Item")
            .price(12D)
            .build();
    }
}
