package pl.tu.kielce.pizza.be.item.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.be.item.model.jpa.Item;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;

@Component
@RequiredArgsConstructor
public class ItemMapper {

    public ItemDto entityToDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
//        itemDto.setDescription(item.getDescription());
        itemDto.setPrice(item.getPrice());

        return itemDto;
    }

    public Item dtoToEntity(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
//        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());

        return item;
    }

}
