package pl.tu.kielce.pizza.ingredient.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.ingredient.dto.ItemDto;
import pl.tu.kielce.pizza.ingredient.model.jpa.Item;

@Component
@RequiredArgsConstructor
public class ItemMapper {

    public ItemDto entityToDto(Item entity) {
        return ItemDto
                .builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .name(entity.getName())
                .build();
    }

    public Item dtoToEntity(ItemDto dto) {
        return Item
                .builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .name(dto.getName())
                .build();
    }
}
